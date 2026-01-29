package com.Oran.Makizone.Controller;

import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.Product;
import com.Oran.Makizone.Database.HibernateJPA.Model.Trade.User;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Catalog.ProductRepo;
import com.Oran.Makizone.Database.HibernateJPA.Service.Trade.OrderService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Trade.UserService;
import com.Oran.Makizone.Utilities.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final ProductRepo productRepo;
    private final OrderService orderService;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public MarketController(ProductRepo productRepo, OrderService orderService, TokenService tokenService,
            UserService userService) {
        this.productRepo = productRepo;
        this.orderService = orderService;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        // Limit to 6 items to avoid clutter
        return ResponseEntity.ok(productRepo.findAll().stream().limit(6).toList());
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload) {
        String email = tokenService.extractMail(token);
        User user = userService.findUser(email);

        if (!payload.containsKey("productId") || !payload.containsKey("quantity")) {
            throw new RuntimeException("Missing productId or quantity");
        }

        Long productId = Long.valueOf(payload.get("productId").toString());
        Integer quantity = Integer.valueOf(payload.get("quantity").toString());

        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        orderService.placeOrder(user, product, quantity);

        return ResponseEntity
                .ok("Purchase successful! " + quantity + " " + product.getName() + "(s) added to inventory.");
    }
}
