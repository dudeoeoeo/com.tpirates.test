package com.rest.test.service;

import com.rest.test.domain.delivery.Delivery;
import com.rest.test.domain.delivery.DeliveryRepository;
import com.rest.test.domain.option.Option;
import com.rest.test.domain.product.Product;
import com.rest.test.domain.product.ProductRepository;
import com.rest.test.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final DeliveryRepository deliveryRepository;

    private final StoreService storeService;

    public List<Map<String, String>> findAll() {
        List<Product> productList = productRepository.findAll();
        List<Map<String, String>> list = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getInstance();

        for(int i = 0; i < productList.size(); i++) {
            List<Option> optionList = productList.get(i).getOptions();
            int minPrice = Integer.MAX_VALUE;

            for(int j = 0; j < optionList.size(); j++) {
                minPrice = Math.min(minPrice, optionList.get(j).getPrice());
            }
            Map<String, String> map = new HashMap<>();
            map.put("name", productList.get(i).getName());
            map.put("description", productList.get(i).getDescription());
            map.put("price", numberFormat.format(minPrice)+" ~ ");

            list.add(map);
        }

        return list;
    }

    @Transactional
    public Product save(Product newProduct) {
        Store store = storeService.save(new Store());
        newProduct.setStore(store);
        return productRepository.save(newProduct);
    }

    @Transactional
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Map<String, String>> delivery(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return null;
        }
        int day = 0;

        String closeTime = LocalDate.now() + " " + product.getDelivery().getClosing();

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime closingTime = LocalDateTime.parse(closeTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if(product.getDelivery().getType().toLowerCase(Locale.ROOT).equals("regular")) { // regular = 익일발송 + 1, 마감시간이 지났다면 +1
            day++;
        }
        if(localDateTime.isAfter(closingTime)) {
            day++;
        }
        LocalDate localDate = LocalDate.now().plusDays(day);
        List<Map<String, String>> list = new ArrayList<>();

        for(int i = 0; list.size() < 5; i++) {
            day = localDate.plusDays(i).getDayOfWeek().getValue();

            if((day == 7) || (day == 1 && list.size() == 0) || (day == 6 && list.size() == 0)) continue;

            String [] monthAndDay = String.valueOf(localDate.plusDays(i)).split("-");
            Map<String, String> map = new HashMap<>();
            map.put("date", monthAndDay[1]+"월 "+monthAndDay[2]+"일 "+ localDate.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
            list.add(map);
        }

        return list;
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
