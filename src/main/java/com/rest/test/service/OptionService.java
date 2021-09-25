package com.rest.test.service;

import com.rest.test.domain.option.Option;
import com.rest.test.domain.option.OptionRepository;
import com.rest.test.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Transactional
    public void saveAll(List<Option> optionList, Product product) {
        /*
        for(int i = 0; i < optionList.size(); i++) {
            optionList.get(i).setProduct(product);
            System.out.println("option: "+product.getId());
        }

         */
        optionRepository.saveAll(optionList);
    }
}
