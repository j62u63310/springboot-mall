package com.j62u63310.springbootmall.dao;

import com.j62u63310.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
