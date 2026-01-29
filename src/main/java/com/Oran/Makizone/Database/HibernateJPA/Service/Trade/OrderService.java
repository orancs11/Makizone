package com.Oran.Makizone.Database.HibernateJPA.Service.Trade;

import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.Product;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.Order;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.OrderItem;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Trade.OrderRepo;
import com.Oran.Makizone.Database.HibernateJPA.Service.Game.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class OrderService {
    private OrderRepo repo;
    @Autowired
    private PlayerProfileService playerProfileService;
    private OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepo repo, PlayerProfileService playerProfileService, OrderItemService orderItemService) {
        this.repo = repo;
        this.playerProfileService = playerProfileService;
        this.orderItemService = orderItemService;
    }

    @Autowired
    private com.Oran.Makizone.Database.HibernateJPA.Repository.Catalog.ProductSKURepo skuRepo;

    public void placeOrder(User user, Product product, int quantity) {
        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(BigDecimal.ZERO); // Free for now
        order.setStatus(com.Oran.Makizone.Utilities.Enums.OrderStatus.PAID);

        // Populate shipping address (required by DB)
        // Convert User's generic address or use a default since these are virtual items
        java.util.Map<String, String> addressMap = new java.util.HashMap<>();
        if (user.getAddress() != null) {
            for (Map.Entry<String, Object> entry : user.getAddress().entrySet()) {
                if (entry.getValue() != null) {
                    addressMap.put(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        if (addressMap.isEmpty()) {
            addressMap.put("type", "Virtual/Digital Delivery");
        }
        order.setShippingAddress(addressMap);

        order = repo.save(order);

        // Find SKU regarding product, or first one
        // Ideally we should select a specific SKU, but for simple "Buy Item" we pick
        // the first available or default active one.
        // Assuming every product has at least one SKU.
        java.util.List<com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.ProductSKU> skus = skuRepo
                .findByProduct(product);
        if (skus.isEmpty()) {
            throw new RuntimeException("No SKU found for product: " + product.getName());
        }
        com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.ProductSKU sku = skus.get(0);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProductSKU(sku);
        item.setQuantity(quantity);
        item.setPriceAtPurchase(BigDecimal.ZERO);

        orderItemService.saveItem(item);

        // Add to inventory
        String categoryId = String.valueOf(product.getCategory().getId());
        playerProfileService.addItemToInventory(user, categoryId, quantity);
    }
}
