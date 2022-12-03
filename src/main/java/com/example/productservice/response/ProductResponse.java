package com.example.productservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@Builder
public class ProductResponse implements Serializable {
    private int id;
    private String name;
    private float price;


}
