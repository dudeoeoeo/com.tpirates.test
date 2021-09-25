package com.rest.test.domain.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.test.domain.BaseTimeEntity;
import com.rest.test.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Option extends BaseTimeEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    @JsonIgnore
    private int stock;

    @Builder
    public Option(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

}
