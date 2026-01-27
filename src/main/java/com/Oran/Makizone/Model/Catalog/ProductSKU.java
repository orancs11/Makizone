package com.Oran.Makizone.Model.Catalog;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(schema = "Makizone-Catalog", name = "product_skus")
public class ProductSKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="sku_code", nullable = false, unique = true)
    private String skuCode;
    @Column(name = "name", nullable = false)
    private String name;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes")
    private Map<String, Object> attributes;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name="stock_quantity")
    private Integer stock_quantity;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "media_url")
    private String mediaUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductSKU{" +
                "id=" + id +
                ", skuCode='" + skuCode + '\'' +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                ", price=" + price +
                ", stock_quantity=" + stock_quantity +
                ", isActive=" + isActive +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", product=" + product +
                '}';
    }

}
