package com.data.dataanalysis.service.impl;

import com.data.dataanalysis.config.EsConfig;
import com.data.dataanalysis.entity.Product;
import com.data.dataanalysis.service.ProductService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Xiemingxuan
 * @date 2023/9/14
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    RestHighLevelClient restHighLevelClient;


    @Override
    public Product selectProduct(Long id) throws IOException {
        SearchRequest request = new SearchRequest("product");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("productId", id));
        request.source(sourceBuilder);

        // 执行查询并处理结果
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        if (response != null && response.status() == RestStatus.OK) {

            SearchHit[] hits = response.getHits().getHits();
            if (hits != null && hits.length > 0) {
                Map<String, Object> sourceMap = hits[0].getSourceAsMap();

                // 从查询结果中获取 viewCount 值，并将其加 1
                int viewCount = ((int) sourceMap.get("viewCount") + 1) + 1;

                // 构建更新请求
                UpdateRequest updateRequest = new UpdateRequest("product", hits[0].getId());
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("viewCount", viewCount);
                updateRequest.doc(updateMap);

                // 执行更新请求
                UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
                if (updateResponse.status() == RestStatus.OK) {
                    System.out.println("viewCount 更新成功！");
                    System.out.println("更新后的 viewCount 值：" + viewCount);
                } else {
                    System.out.println("viewCount 更新失败！");
                }

                Product product = new Product();
                product.setProductId(id);
                product.setProductType((String) sourceMap.get("productType"));
                product.setProductName((String) sourceMap.get("productName"));
                product.setPrice((String) sourceMap.get("price"));
                product.setAddress((String) sourceMap.get("address"));
                product.setColor((String) sourceMap.get("color"));
                product.setProductVersion((String) sourceMap.get("productVersion"));
                product.setViewCount(Long.valueOf(String.valueOf(sourceMap.get("viewCount"))));
                return product;
            }
        }

        return null;
    }


}