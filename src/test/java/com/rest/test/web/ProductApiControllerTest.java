package com.rest.test.web;

import com.rest.test.domain.delivery.Delivery;
import com.rest.test.domain.option.Option;
import com.rest.test.domain.product.Product;
import com.rest.test.domain.product.ProductRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @After
    public void tearDown() throws Exception {
        productRepository.deleteAll();
    }

    @Test
    public void newProduct_등록() throws Exception {
        // given
        String type = "fast";
        String closing = "12:00";
        Delivery delivery = Delivery.builder()
                .type(type)
                .closing(closing)
                .build();

        String name = "노르웨이산 연어";
        String description = "노르웨이산 연어 300g, 500g, 반마리 필렛";
        Product product = Product.builder()
                .name(name)
                .description(description)
                .build();

        Product savedProduct = productRepository.save(product);
        Long product_id = savedProduct.getId();

        product.setId(product_id);

        String option_name = "대 7~8미";
        int price = 50000;
        int stock = 99;
        Option option1 = Option.builder()
                .name(option_name)
                .price(price)
                .stock(stock)
                .build();

        option_name = "중 14~15미";
        price = 34000;
        stock = 99;
        Option option2 = Option.builder()
                .name(option_name)
                .price(price)
                .stock(stock)
                .build();

        String url = "http://localhost:" + port + "/api/v1/product_add";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, product, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Product> all = productRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getDescription()).isEqualTo(description);
    }
}
