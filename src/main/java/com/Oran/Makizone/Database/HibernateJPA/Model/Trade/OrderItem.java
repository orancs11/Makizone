package com.Oran.Makizone.Database.HibernateJPA.Model.Trade;


import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.ProductSKU;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(schema = "Makizone-Trade", name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "price_at_purchase", nullable = false)
    private BigDecimal priceAtPurchase;

    @JoinColumn(name = "order_id", nullable = false)
    @ManyToOne
    private Order order;

    @JoinColumn(name = "product_sku_id", nullable = false)
    @ManyToOne
    private ProductSKU productSKU;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductSKU getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(ProductSKU productSKU) {
        this.productSKU = productSKU;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", priceAtPurchase=" + priceAtPurchase +
                ", order=" + order +
                ", productSKU=" + productSKU +
                '}';
    }
}
