package com.example.productservice.services.impl;


import com.example.productservice.dto.ProductDto;
import com.example.productservice.model.Product;
import com.example.productservice.model.User;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.repositories.UserRepository;
import com.example.productservice.response.ProductResponse;
import com.example.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product>products = productRepository.findAll();
        List<ProductResponse> productResponses=new ArrayList<>();
        for(Product product : products){
            ProductResponse productResponse=ProductResponse.builder().build();
            BeanUtils.copyProperties(product,productResponse);
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    @Transactional
    public ProductResponse addProduct(ProductDto productDto) {
        String username= getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(()->new BadCredentialsException("User not found"));
        Product product=new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setUser(user);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.saveAndFlush(product);
        ProductResponse productResponse = ProductResponse.builder().build();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Integer id,ProductDto productDto) {
       String username = getUsername();
       Product product = productRepository.findById(id).orElseThrow(()->new BadCredentialsException("product not found"));

       if(!product.getUser().getUsername().equals(username)){
        throw new BadCredentialsException("This product not your product. You can not modify this product. ");
       }
       product.setUpdatedAt(LocalDateTime.now());
       product.setName(productDto.getName());
       product.setPrice(productDto.getPrice());
       productRepository.saveAndFlush(product);
       ProductResponse productResponse = ProductResponse.builder().build();
       BeanUtils.copyProperties(product,productResponse);
       return productResponse;
    }

    @Override
    @Transactional
    public String deleteProduct(Integer id ) {
        String username = getUsername();
        Product product = productRepository.findById(id).orElseThrow(() -> new BadCredentialsException("product not found"));

        if (!product.getUser().getUsername().equals(username)) {
            throw new BadCredentialsException("This product not your product. You can not delete this product. ");
        }
        productRepository.deleteById(id);
        return "Product deleted successfully";

    }
    private String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
