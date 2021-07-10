package com.txtbits.orders.service;

import com.txtbits.orders.model.Order;
import com.txtbits.orders.repository.OrderRepository;
import com.txtbits.orders.util.CSVHelper;
import com.txtbits.orders.util.ConvertJavaMapToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public List<Order> getAllOrders() {
        return repository.findAllByOrderByOrderIdAsc();
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Order> orders = CSVHelper.csvToOrders(file.getInputStream());
            repository.saveAll(orders);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream load() {
        List<Order> orders = getAllOrders();

        return CSVHelper.ordersToCSV(orders);
    }

    @Override
    public String showCounts() {
        Map<String, Map<String, Integer>> counts = new HashMap<>();
        getCountsByKPI("region", counts);
        getCountsByKPI("country", counts);
        getCountsByKPI("itemType", counts);
        getCountsByKPI("salesChannel", counts);
        getCountsByKPI("orderPriority", counts);
        return ConvertJavaMapToJson.convert(counts);
    }

    private void getCountsByKPI(String KPI, Map<String, Map<String, Integer>> counts) {
        List<Object[]> countTotalsByKPI = new ArrayList<>();
        switch (KPI) {
            case "region":
                countTotalsByKPI = repository.countTotalsByRegion();
                break;
            case "country":
                countTotalsByKPI = repository.countTotalsByCountry();
                break;
            case "itemType":
                countTotalsByKPI = repository.countTotalsByItemType();
                break;
            case "salesChannel":
                countTotalsByKPI = repository.countTotalsBySalesChannel();
                break;
            case "orderPriority":
                countTotalsByKPI = repository.countTotalsByOrderPriority();
                break;
            default:
                break;
        }
       counts.put(KPI, getSubsetsByKPI(countTotalsByKPI));
    }

    private Map<String, Integer> getSubsetsByKPI(List<Object[]> countTotalsByKPI) {
        Map<String, Integer> countsBySubset = new HashMap<>();
        for (Object[] r : countTotalsByKPI) {
            String name = (String) r[0];
            Integer count = ((Number) r[1]).intValue();
            countsBySubset.put(name, count);
        }
        return countsBySubset;
    }

}

