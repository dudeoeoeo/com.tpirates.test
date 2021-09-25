package com.rest.test.domain.product;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //@EntityGraph(attributePaths = {"store","options"})
    @Query(value = "select * from product p left join option o on p.id = o.id order by p.create_date desc, o.price;", nativeQuery = true)
    List<Product> findAll();


}