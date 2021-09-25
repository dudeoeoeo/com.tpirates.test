package com.rest.test.domain.delivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.test.domain.BaseTimeEntity;
import com.rest.test.domain.product.Product;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery extends BaseTimeEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String closing;

    @Builder
    public Delivery(String type, String closing) {
        this.type = type;
        this.closing = closing;
    }

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "delivery")
    private Product product;


}
