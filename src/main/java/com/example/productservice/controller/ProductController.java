package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.response.ProductResponse;
import com.example.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductDto productDto) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto));
    }

    @GetMapping("/list-all-products")
    public ResponseEntity<List<ProductResponse>> listProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PutMapping("/update-product")
    public ResponseEntity<ProductResponse> updateProduct(@RequestParam("id") Integer id, @RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(id,productDto));
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }


}
