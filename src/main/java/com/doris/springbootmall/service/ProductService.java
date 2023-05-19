package com.doris.springbootmall.service;

import com.doris.springbootmall.constant.ProductCategory;
import com.doris.springbootmall.dto.ProductRequest;
import com.doris.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);

//    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);


    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);



}
