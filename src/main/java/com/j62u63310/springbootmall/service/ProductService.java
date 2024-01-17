package com.j62u63310.springbootmall.service;


import com.j62u63310.springbootmall.dto.ProductRequest;
import com.j62u63310.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProduct(Integer productId);
}
