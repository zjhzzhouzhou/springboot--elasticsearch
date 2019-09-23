package com.zz.elasticsearch.repository;

import com.zz.elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-09-23
 * Time: 10:24
 */
public interface BookRepository extends ElasticsearchRepository<Book, String> {
}
