package com.zz.elasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-09-23
 * Time: 14:37
 */

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.zz.elasticsearch.repository")
public class ElasticSearchConfig {
}
