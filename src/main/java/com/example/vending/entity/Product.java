package com.example.vending.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String title;

    private String content;
}
