package com.rest.test.service;

import com.rest.test.domain.delivery.Delivery;
import com.rest.test.domain.delivery.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }
}
