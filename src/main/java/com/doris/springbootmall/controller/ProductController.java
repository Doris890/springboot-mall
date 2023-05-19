package com.doris.springbootmall.controller;

import com.doris.springbootmall.constant.ProductCategory;
import com.doris.springbootmall.dto.ProductQueryParams;
import com.doris.springbootmall.dto.ProductRequest;
import com.doris.springbootmall.model.Product;
import com.doris.springbootmall.service.ProductService;
import com.doris.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

    /**
          * 查詢商品列表 - 分頁 + 總筆數
          */
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            // 查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            // 排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            // 分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);



        // 取得 product list
        List<Product> productList = productService.getProducts(productQueryParams);

        // 計算 product 總數
        Integer total = productService.countProduct(productQueryParams);

        // 分頁返回給前端
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResult(productList);


        return ResponseEntity.status(HttpStatus.OK).body(page);

    }

//    /**
//     * 查詢商品列表 - 依據商品分類
//     *
//     * @return
//     */
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getProducts(
//            // 查詢條件 Filtering
//            @RequestParam(required = false) ProductCategory category,
//            @RequestParam(required = false) String search,
//
//            // 排序 Sorting
//            @RequestParam(defaultValue = "created_date") String orderBy,
//            @RequestParam(defaultValue = "desc") String sort,
//
//            // 分頁 Pagination
//            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
//            @RequestParam(defaultValue = "0") @Min(0) Integer offset
//    ) {
//
//        ProductQueryParams productQueryParams = new ProductQueryParams();
//        productQueryParams.setCategory(category);
//        productQueryParams.setSearch(search);
//        productQueryParams.setOrderBy(orderBy);
//        productQueryParams.setSort(sort);
//        productQueryParams.setLimit(limit);
//        productQueryParams.setOffset(offset);
//
//
//        // RequestParam 從URL中取得請求參數
////        List<Product> productList = productService.getProducts(category, search);
//        List<Product> productList = productService.getProducts(productQueryParams);
//
//
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
//
//    }

//    /**
//     * 查詢商品列表
//     *
//     * @return
//     */
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getProducts() {
//
//        List<Product> productList = productService.getProducts();
//
//
//
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
//
//    }

    /**
     * 查詢單一商品
     *
     * @param productId
     * @return
     */
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


    /**
     * 新增商品
     *
     * @param productRequest
     * @return
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Validated ProductRequest productRequest) {

        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);


    }


    /**
     * 修改商品功能
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Validated ProductRequest productRequest) {

        //1. 接住從URL路徑傳過來的product的值 @PathVariable，  接住前端所傳過來的json參數 @RequestBody ，@Validated : notNull註解才會生效
        //2. 可以沿用ProductRequest，是因為剛好只有這class的變數才能允許修改，限制前端只能修改ProductRequest的值
        //3. 可以先用id檢查商品是否存在


        //檢查product是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        //修改商品的數據
        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        //回傳前端商品更新成功，以及更新後的值
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);

    }


    /**
     * 刪除商品
     *
     * @return
     */
    @DeleteMapping("products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {


        productService.deleteProductById(productId);

        //NO_CONTENT : 表示數據已經被刪除了
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
