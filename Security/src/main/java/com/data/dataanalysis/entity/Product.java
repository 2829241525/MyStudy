package com.data.dataanalysis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xiemingxuan
 * @date 2023/9/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    //产品id
    private Long productId;
    //产品名
    private String productName;
    //产品类型
    private String productType;
    //产品版本
    private String productVersion;
    //价格
    private String price;
    //地址
    private String address;
    //颜色
    private String color;
    //浏览量
    private Long viewCount;
    //购买量
    private Long buyCount;
    //加入购物车数量
    private Long addCartCount;
    //购买量与浏览量比例 参数一
    private Float parame1;
    //购买量与浏览量比例 参数二
    private Float parame2;

    /*.............*/
}