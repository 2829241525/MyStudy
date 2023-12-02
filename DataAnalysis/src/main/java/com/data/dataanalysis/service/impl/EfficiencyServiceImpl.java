package com.data.dataanalysis.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.data.dataanalysis.config.redis.template.RedisHashStringRepos;
import com.data.dataanalysis.entity.Product;
import com.data.dataanalysis.service.EfficiencyService;
import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class EfficiencyServiceImpl implements EfficiencyService {

    @Resource
    RedisHashStringRepos redisHashStringRepos;

    //guava 缓存使用
    private LoadingCache<String, String> loadCache = CacheBuilder.newBuilder()
            .initialCapacity(1).maximumSize(2)
            .expireAfterWrite(60 * 60 * 24, TimeUnit.SECONDS)
            .removalListener(
                    new RemovalListener<String, String>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, String> notification) {
                                log.info(notification.getKey() + ":remove==> :" + notification.getValue());
                        }
                    }
            ).build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    log.info("查询地图船只所有数据，更新缓存");
                    List<String[]> resultList = new ArrayList<>();
                    List<String> myList = new ArrayList<>();

                    //转双重数组
                    myList.stream().forEach(s->{
                        String[] array ={
                                new BigDecimal(Optional.ofNullable(s).orElse("0")).toString()
                        };
                        resultList.add(array);
                    });
                    String[][] array = resultList.toArray(new String[0][]);
                    return Arrays.deepToString(array);
                }
            });


    @Override
    public Product selectProduct(Long id) throws IOException {

        try {
            //查询guava
            loadCache.get("all_10000");

            //查询数据库，置入缓存
            //写入缓存，hash结构，同时过期,多条原子操作
            redisHashStringRepos.executeAtomicRedisOperations(() -> {
                redisHashStringRepos.add("all_10000", com.alibaba.fastjson2.JSONObject.toJSONString("15332"));
                //过期时间 2*60*60s
                redisHashStringRepos.expire("all_10000", 2 * 60 * 60);
            });
            getVesselListReposByCache("all_10000");

        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }


    private void getVesselListReposByCache(String key) {
        //对象转list
        List<Objects> all1000 = JSON.parseArray(
                Optional.ofNullable(redisHashStringRepos.load(key, "all_1000"))
                        .map(String::toString).orElse("[]")
                , Objects.class);

        //转特定结构
        List<List<Objects>> lists = JSONObject.parseObject(
                Optional.ofNullable(redisHashStringRepos.findMany(key, new ArrayList<>()))
                        .map(String::toString).orElse("[[]]")
                ,
                new TypeReference<List<List<Objects>>>() {
                }
        );

        /**
         * flatMap 方法需要一个函数作为参数，该函数将每个元素映射为一个流。然后，将这些流合并为一个新的流。这就是为什么最终的流被称为"平铺"的原因。
         * List<List<Integer>> numbers = Arrays.asList(
         *     Arrays.asList(1, 2, 3),
         *     Arrays.asList(4, 5, 6),
         *     Arrays.asList(7, 8, 9)
         * );
         *
         * List<Integer> flattened = numbers.stream()
         *         .flatMap(List::stream)
         *         .collect(Collectors.toList());
         *
         * System.out.println(flattened);
         * [1, 2, 3, 4, 5, 6, 7, 8, 9]
         */
        lists.stream().flatMap(s -> s == null ? Stream.empty() : s.stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


}
}