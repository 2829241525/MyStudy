package com.data.dataanalysis.service;
/**
 * @author Xiemingxuan
 * @date 2023/9/14
 */

import com.data.dataanalysis.entity.Product;

import java.io.IOException;

/**
 * @author Xiemingxuan
 * @date 2023/9/14
 */
public interface ProductService {

    Product selectProduct(Long id) throws IOException;
}
