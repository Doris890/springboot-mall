package com.doris.springbootmall.rowmapper;

import com.doris.springbootmall.constant.ProductCategory;
import com.doris.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultset, int i) throws SQLException {


        Product product = new Product();

        //和DB欄位對應，注意欄位資料型態
        product.setProductId(resultset.getInt("product_id"));
        product.setProductName(resultset.getString("product_name"));





//        //資料庫取出是String類型，但product的category是productCategory類型
//        String categoryStr = resultset.getString("category");
//        ProductCategory category = ProductCategory.valueOf(categoryStr);
//        product.setCategory(category);
        product.setCategory(ProductCategory.valueOf(resultset.getString("category")));

        product.setImageUrl(resultset.getString("image_url"));
        product.setPrice(resultset.getInt("price"));
        product.setStock(resultset.getInt("stock"));
        product.setDescription(resultset.getString("description"));
        product.setCreatedDate(resultset.getTimestamp("created_date"));
        product.setLastModifiedDate(resultset.getTimestamp("last_modified_date"));

        return product;

    }
}
