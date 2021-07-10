package com.txtbits.orders.service;

import com.txtbits.orders.model.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrder(Long id);

    ByteArrayInputStream load();

    void save(MultipartFile file);

    String showCounts();

}
