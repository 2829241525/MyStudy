package com.data.dataanalysis.controller;


import com.data.dataanalysis.entity.Product;
import com.data.dataanalysis.service.EfficiencyService;
import com.data.dataanalysis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/efficient")
@Validated
public class EfficiencyController {

    private final EfficiencyService efficiencyService;

    /**
     * 设置默认
     */
    @GetMapping("/query/{id}")
    public Product setDefault(@PathVariable("id") Long id) throws IOException {

        Product product = efficiencyService.selectProduct(id);
        return product;
    }




}
