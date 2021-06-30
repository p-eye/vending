package com.example.vending.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    @Id @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}
