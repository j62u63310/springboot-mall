package com.j62u63310.springbootmall.dao;

import com.j62u63310.springbootmall.constant.ProductCategory;
import com.j62u63310.springbootmall.dto.ProductQueryParams;
import com.j62u63310.springbootmall.dto.ProductRequest;
import com.j62u63310.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);
}
