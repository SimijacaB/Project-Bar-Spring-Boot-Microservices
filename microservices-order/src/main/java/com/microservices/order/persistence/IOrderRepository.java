package com.microservices.order.persistence;

import com.microservices.order.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long>{

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
