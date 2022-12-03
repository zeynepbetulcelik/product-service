package com.example.productservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;


    private String name;

    private String surname;

    private String username;

    private String password;

    private boolean active;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();



}