package com.doris.springbootmall.service.impl;

import com.doris.springbootmall.dao.ProductDao;
import com.doris.springbootmall.dto.ProductQueryParams;
import com.doris.springbootmall.dto.ProductRequest;
import com.doris.springbootmall.model.Product;
import com.doris.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);
    }

//    @Override
//    public List<Product> getProducts(ProductCategory category, String search) {
//        return productDao.getProducts(category,search);
//    }

//    @Override
//    public List<Product> getProducts() {
//        return productDao.getProducts();
//    }

    /**
     * service層的實作比較簡單，就只要去call ProductDao
     * 的getProductById方法就可以了
     *
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {

        productDao.deleteProductById(productId);
    }


}
