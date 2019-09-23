package com.zz.elasticsearch.controller;

import com.zz.elasticsearch.bean.Book;
import com.zz.elasticsearch.bean.PageReq;
import com.zz.elasticsearch.repository.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-09-23
 * Time: 9:54
 */
@Api("/es相关测试")
@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation("新增数据")
    @PostMapping("/insert")
    public Object insertESData(@RequestBody Book book) {
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(book.getId())
                .withObject(book)
                .build();
        String id = elasticsearchTemplate.index(indexQuery);

        return id;
    }

    @ApiOperation("批量新增西瓜金刚数据")
    @PostMapping("/batchInsert")
    public Object insertESDataByBatch() {

        List<IndexQuery> indexQueries = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = Book.builder().id(i + "").bookName(i + "号西瓜金刚")
                    .content("这是讲述超人之间的故事第" + i + "回").pagecount(10 * i).build();
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(book.getId())
                    .withObject(book)
                    .build();
            indexQueries.add(indexQuery);
        }

        elasticsearchTemplate.bulkIndex(indexQueries);

        return "插入成功";
    }

    @ApiOperation("批量新增钢铁侠数据")
    @PostMapping("/batchInsert2")
    public Object insertESDataByBatch2() {

        List<IndexQuery> indexQueries = new ArrayList<>();
        for (int i = 100; i <200; i++) {
            Book book = Book.builder().id(i + "").bookName(i + "号钢铁侠")
                    .content("这是讲述钢铁侠与美国队长之间的故事第" + i + "回").pagecount(10 * i).build();
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(book.getId())
                    .withObject(book)
                    .build();
            indexQueries.add(indexQuery);
        }

        elasticsearchTemplate.bulkIndex(indexQueries);

        return "插入成功";
    }

    @ApiOperation("分页查询数据")
    @GetMapping("/queryForList")
    public Object queryForList( PageReq<String> req) {

        // page:   代表第几页 从0开始
        // size：  代表每页的数量
        Pageable pageable = PageRequest.of(req.getPage(),req.getPageSize());
        //创建查询对象
        MultiMatchQueryBuilder builder = new MultiMatchQueryBuilder(req.getSearchModel(),"bookName","content");
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .withSort(new FieldSortBuilder("pagecount").order(SortOrder.DESC))
                ;

        Page<Book> books = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), Book.class);
        return books;
    }

}
