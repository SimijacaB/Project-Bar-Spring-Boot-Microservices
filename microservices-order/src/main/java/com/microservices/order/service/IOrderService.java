package com.microservices.order.service;

import com.microservices.order.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IOrderService {

    List<Order> finAll();

    Order finById(Long id);

    void save(Order order);

    //Método para obtener listado de ordenes por cliente
    List<Order> findByClientName(String clientName);

    //Método para obtener listado de ordenes por mesa
    List<Order> findByTableNumber(int tableNumer);

    //Método para obtener listado de ordenes por id del mesero
    List<Order> findByWaiterId(String waiterId);

    //Método para obtener listado de ordenes per fecha
    List<Order> findByDate(String date);

    //Método para obtener listado de ordenes por estado
    List<Order> findByStatus(String status);

}
