package com.doris.springbootmall.dto;

import com.doris.springbootmall.constant.ProductCategory;

import javax.validation.constraints.NotNull;

/**
 * 決定前端要傳哪些參數進來
 * 可以在這邊設定NULL檢驗     @NotNull
 */
public class ProductRequest {

    @NotNull
    private String productName;


    //當前端傳進來參數不存在ProductCategory中，SpringBoot就會發生轉換錯誤，因此會回傳400狀態碼給前端
    @NotNull
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
