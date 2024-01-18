package com.j62u63310.springbootmall.model;

import com.j62u63310.springbootmall.constant.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private ProductCategory category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
}
