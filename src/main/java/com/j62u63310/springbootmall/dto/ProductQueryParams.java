package com.j62u63310.springbootmall.dto;

import com.j62u63310.springbootmall.constant.ProductCategory;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ProductQueryParams {
    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
    private Integer limit;
    private Integer offset;
}
