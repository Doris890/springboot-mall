package com.doris.springbootmall.service.impl;

import com.doris.springbootmall.dao.ProductDao;
import com.doris.springbootmall.model.Product;
import com.doris.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * service層的實作比較簡單，就只要去call ProductDao
     * 的getProductById方法就可以了
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
