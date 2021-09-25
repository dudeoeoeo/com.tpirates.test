package com.rest.test.service;

import com.rest.test.domain.store.Store;
import com.rest.test.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Store save(Store store) {
        return storeRepository.save(store);
    }
}
