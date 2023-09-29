package com.data.dataanalysis.main;

import com.data.dataanalysis.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Xiemingxuan
 * @date 2023/9/28
 */
public class Test {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        IndexRequest request = new IndexRequest("product");

        ArrayList<Product> list = new ArrayList<>();
        Product product3 = new Product(1L, "华为（HUAWEI）旗舰手机 Mate 60", "手机", "12GB+512GB", "6999", "北京", "红", 0L, 0L,0L,0f,0f);
        list.add(product3);
        Product product = new Product(2L, "vivo iQOO Neo8", "手机", "12GB+512GB", "2799", "北京", "白", 0L, 0L,0L,0f,0f);
        list.add(product);
        Product product1 = new Product(3L, "OPPO 一加 Ace 2", "手机", "12GB+512GB", "2899", "北京", "橙色", 0L, 0L,0L,0f,0f);
        list.add(product1);
        Product product2 = new Product(4L, "Apple 苹果", "手机", "12GB+512GB", "9199", "北京", "蓝色", 0L, 0L,0L,0f,0f);
        list.add(product2);
        Product product4 = new Product(5L, "荣耀70 IMX800", "手机", "12GB+512GB", "3099", "北京", "墨绿清", 0L, 0L,0L,0f,0f);
        list.add(product4);


        list.forEach(s -> {

                    String jsonString = convertObjectToJson(s);
                    request.source(jsonString, XContentType.JSON);
                    try {
                        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

    }

    private static String convertObjectToJson(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}