package com.example.productservice.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Float price;


    @CreatedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
