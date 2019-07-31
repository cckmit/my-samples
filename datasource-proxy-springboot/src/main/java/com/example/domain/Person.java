package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
@Entity
@Table(name = "persons")
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
}
