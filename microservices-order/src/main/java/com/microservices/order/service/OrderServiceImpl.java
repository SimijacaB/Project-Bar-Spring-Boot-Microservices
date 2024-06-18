package com.microservices.order.service;

import com.microservices.order.entities.Order;
import com.microservices.order.persistence.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderRepository orderRepository;

    public OrderServiceImpl(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> finAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order finById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> findByClientName(String clientName) {
        return orderRepository.findByClientName(clientName);
    }

    @Override
    public List<Order> findByTableNumber(int tableNumer) {
        return orderRepository.findByTableNumber(tableNumer);
    }

    @Override
    public List<Order> findByWaiterId(String waiterId) {
        return orderRepository.findByWaiterId(waiterId);
    }

    @Override
    public List<Order> findByDate(String date) {
        return orderRepository.findByDate(date);
    }

    @Override
    public List<Order> findByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
