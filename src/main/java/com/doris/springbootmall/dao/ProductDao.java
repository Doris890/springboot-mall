package com.doris.springbootmall.dao;

import com.doris.springbootmall.dto.ProductRequest;
import com.doris.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);


    Integer createProduct(ProductRequest productRequest);


    void updateProduct(Integer productId, ProductRequest productRequest);


    void deleteProductById(Integer productId);


}
