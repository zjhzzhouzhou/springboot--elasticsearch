package com.zz.elasticsearch.bean;

import lombok.Data;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-09-23
 * Time: 14:15
 */
@Data
public class PageReq<T> {

    private int page;
    private int pageSize;
    private T searchModel;
}
