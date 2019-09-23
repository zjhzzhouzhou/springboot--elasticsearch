package com.zz.elasticsearch.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-09-23
 * Time: 10:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "bookstore", type="book")
public class Book {

    private  String id; //编号
    private String bookName;  //书名
    private String content;     //内容主题
    private int pagecount;      //多少页

}
