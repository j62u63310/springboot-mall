package com.j62u63310.springbootmall.util;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<T> result;
}
