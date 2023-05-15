package com.doris.springbootmall.controller;

import com.doris.springbootmall.dto.ProductRequest;
import com.doris.springbootmall.model.Product;
import com.doris.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        //@PathVariable productId : 表示是從url路徑中傳遞進來
        //取得某一筆商品數據 => 使用get方法來請求   (前端請求數據)
        //獲得某id商品的數據資料 => 需要先注入service   (從資料庫撈出數據)
        //回傳Product 類型json給前端
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Validated ProductRequest productRequest) {

        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);


    }

}
