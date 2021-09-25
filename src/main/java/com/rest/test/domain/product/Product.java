package com.rest.test.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.test.domain.BaseTimeEntity;
import com.rest.test.domain.delivery.Delivery;
import com.rest.test.domain.option.Option;
import com.rest.test.domain.store.Store;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Builder
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    @JsonIgnore
    private Store store;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Option> options;


}
