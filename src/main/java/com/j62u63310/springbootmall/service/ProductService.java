package com.j62u63310.springbootmall.service;


import com.j62u63310.springbootmall.dto.ProductRequest;
import com.j62u63310.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);
}
