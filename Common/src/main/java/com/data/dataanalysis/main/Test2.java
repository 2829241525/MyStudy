package com.data.dataanalysis.main;

import com.data.dataanalysis.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Xiemingxuan
 * @date 2023/9/28
 */
public class Test2 {



    public static void main(String[] args) throws IOException, InterruptedException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));


        Thread viewCount = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    viewCount(restHighLevelClient);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        viewCount.start();


        Thread buyCount = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    buyCount(restHighLevelClient);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        buyCount.start();

        Thread addCartCount = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    addCartCount(restHighLevelClient);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        addCartCount.start();

    }

    public static int generateRandomNumber(int param1, int param2, int param3, int param4, int param5) {
        Random random = new Random();

        // 计算所有参数之和
        int sum = param1 + param2 + param3 + param4 + param5;

        // 生成一个0到总和之间的随机数
        int randomNumber = random.nextInt(sum);

        // 根据随机数与各参数的大小关系来确定返回值
        if (randomNumber < param1) {
            return 1;  // 返回param1
        } else if (randomNumber < param1 + param2) {
            return 2;  // 返回param2
        } else if (randomNumber < param1 + param2 + param3) {
            return 3;  // 返回param3
        } else if (randomNumber < param1 + param2 + param3 + param4) {
            return 4;  // 返回param4
        } else {
            return 5;  // 返回param5
        }
    }

    private static void addCartCount(RestHighLevelClient restHighLevelClient) throws InterruptedException, IOException {
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            int id = generateRandomNumber(18, 3, 5, 8, 1);
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
                    int addCartCount = ((int) sourceMap.get("addCartCount") + 1);
                    int viewCount = ((int) sourceMap.get("viewCount") + 1);

                    // 构建更新请求
                    UpdateRequest updateRequest = new UpdateRequest("product", hits[0].getId());
                    Map<String, Object> updateMap = new HashMap<>();
                    updateMap.put("addCartCount", addCartCount);
                    updateMap.put("viewCount", viewCount);

                    float param1 = (viewCount != 0) ? ((float) (int)sourceMap.get("buyCount") / (int)sourceMap.get("viewCount")) : 0;
                    float param2 = (addCartCount != 0) ? ((float) (int)sourceMap.get("buyCount") / (int) sourceMap.get("addCartCount")) : 0;
                    updateMap.put("parame1", String.valueOf(param1));
                    updateMap.put("parame2", String.valueOf(param2));
                    updateRequest.doc(updateMap);

                    // 执行更新请求
                    restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
                }
            }

        }
    }

    private static void buyCount(RestHighLevelClient restHighLevelClient) throws InterruptedException, IOException {
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(3000);
            int id = generateRandomNumber(12, 1, 1, 4, 2);
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
                    int buyCount = (int) sourceMap.get("buyCount") + 1;
                    int addCartCount = ((int) sourceMap.get("addCartCount") + 1);
                    int viewCount = ((int) sourceMap.get("viewCount") + 1);

                    // 构建更新请求
                    UpdateRequest updateRequest = new UpdateRequest("product", hits[0].getId());
                    Map<String, Object> updateMap = new HashMap<>();
                    updateMap.put("viewCount", viewCount);
                    updateMap.put("buyCount", buyCount);
                    updateMap.put("addCartCount", addCartCount);

                    float param1 = (viewCount != 0) ? ((float) (int)sourceMap.get("buyCount") / (int)sourceMap.get("viewCount")) : 0;
                    float param2 = (addCartCount != 0) ? ((float) (int)sourceMap.get("buyCount") / (int) sourceMap.get("addCartCount")) : 0;
                    updateMap.put("parame1", String.valueOf(param1));
                    updateMap.put("parame2", String.valueOf(param2));
                    updateRequest.doc(updateMap);

                    // 执行更新请求
                    restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
                }
            }

        }
    }


    private static void viewCount(RestHighLevelClient restHighLevelClient) throws InterruptedException, IOException {
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(200);
            int id = generateRandomNumber(5, 2, 3, 5, 1);
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
                    int viewCount = ((int) sourceMap.get("viewCount") + 1);

                    // 构建更新请求
                    UpdateRequest updateRequest = new UpdateRequest("product", hits[0].getId());
                    Map<String, Object> updateMap = new HashMap<>();
                    updateMap.put("viewCount", viewCount);

                    float param1 = (viewCount != 0) ? ((float) (int)sourceMap.get("buyCount") / (int)sourceMap.get("viewCount")) : 0;
                    float param2 = ((int) sourceMap.get("addCartCount") != 0) ? ((float) (int)sourceMap.get("buyCount") / (int) sourceMap.get("addCartCount")) : 0;
                    updateMap.put("parame1", String.valueOf(param1));
                    updateMap.put("parame2", String.valueOf(param2));
                    updateRequest.doc(updateMap);

                    // 执行更新请求
                    restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

                }
            }

        }
    }
}