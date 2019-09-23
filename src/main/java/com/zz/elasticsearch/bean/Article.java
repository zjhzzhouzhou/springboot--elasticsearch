package com.zz.elasticsearch.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-09-23
 * Time: 9:56
 */
@Data
public class Article {

    private String title;
    private String context;
    private BigDecimal price;
    private String type;
}
