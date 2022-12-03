package com.example.productservice.services;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse addProduct(ProductDto productDto);
    ProductResponse updateProduct(Integer id,ProductDto productDto);
    String deleteProduct(Integer id);



}
