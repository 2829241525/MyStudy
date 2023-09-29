package com.data.dataanalysis.controller;


import com.data.dataanalysis.entity.Product;
import com.data.dataanalysis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;


/**
 * 账号规则模块
 *
 * @author 张湘南
 * @date 2023/02/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Validated
public class ProductController {

    private final ProductService productService;

    /**
     * 设置默认
     */
    @GetMapping("/query/{id}")
    public Product setDefault(@PathVariable("id") Long id) throws IOException {


        Product product = productService.selectProduct(id);
        return product;
    }

    /**
     * 浏览量
     * @return
     * @throws IOException
     */

//    @GetMapping("/query")
//    public void vieVolume() throws IOException {
//        for (int i = 0; i < 1000; i++) {
//            Random random = new Random();
//            int randomNumber = generateRandomNumber(random);
//            Product product = productService.selectProduct((long) randomNumber);
//        }
//    }





    private static int generateRandomNumber() {
        Random rand = new Random();
        int randomNum;

        int probability = rand.nextInt(100);

        if (probability < 50) {
            randomNum = 1;
        } else if (probability < 70) {
            randomNum = 2;
        } else if (probability < 80) {
            randomNum = 3;
        } else if (probability < 90) {
            randomNum = 4;
        } else {
            randomNum = 5;
        }
        return randomNum;
    }


}
