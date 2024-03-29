package com.j62u63310.springbootmall.dao.impl;

import com.j62u63310.springbootmall.dao.ProductDao;
import com.j62u63310.springbootmall.dto.ProductQueryParams;
import com.j62u63310.springbootmall.dto.ProductRequest;
import com.j62u63310.springbootmall.model.Product;
import com.j62u63310.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        sql = addFilteringSql(sql,map,productQueryParams);

        return namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT product_id , product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date FROM product WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        sql = addFilteringSql(sql,map,productQueryParams);

        sql = sql + " ORDER BY " + productQueryParams.getOrderBy()+" "+productQueryParams.getSort();
        sql = sql + " LIMIT " + productQueryParams.getLimit() + " OFFSET " +productQueryParams.getOffset();

        return namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id , product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date FROM product WHERE product_id = :productId";
        Map<String ,Object> map = new HashMap<>();
        map.put("productId",productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(!productList.isEmpty()) return productList.get(0);
        else return null;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Date date = new Date();
        Map<String ,Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("createdDate",date);
        map.put("lastModifiedDate",date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);


        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET " +
                "product_name= :productName, category= :category, image_url= :imageUrl, price= :price, " +
                "stock= :stock, description= :description, last_modified_date= :lastModifiedDate " +
                "WHERE product_id=:productId";

        Date date = new Date();
        Map<String ,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageUrl",productRequest.getImageUrl());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        map.put("lastModifiedDate",date);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id= :productId";
        Map<String ,Object> map = new HashMap<>();
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    private String addFilteringSql(String sql,Map<String, Object> map,ProductQueryParams productQueryParams){
        if(productQueryParams.getCategory() != null){
            sql = sql + " AND category = :category";
            map.put("category",productQueryParams.getCategory().name());
        }
        if(productQueryParams.getSearch() != null){
            sql = sql + " AND product_name LIKE :search";
            map.put("search","%"+productQueryParams.getSearch()+"%");
        }

        return sql;
    }
}
