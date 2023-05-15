package com.doris.springbootmall.dao;

import com.doris.springbootmall.dto.ProductRequest;
import com.doris.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);


    void updateProduct(Integer productId, ProductRequest productRequest);


    void deleteProductById(Integer productId);


}
