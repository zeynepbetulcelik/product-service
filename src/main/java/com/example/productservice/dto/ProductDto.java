package com.example.productservice.dto;

import com.example.productservice.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Builder;
import lombok.Data;


import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductDto implements Serializable {
    private int id;
    private String name;
    private float price;
    private User user;


}
