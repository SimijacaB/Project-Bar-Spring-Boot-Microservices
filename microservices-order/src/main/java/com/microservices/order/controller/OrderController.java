package com.microservices.order.controller;

import com.microservices.order.entities.Order;
import com.microservices.order.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody Order order){
        orderService.save(order);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllOrder(){
        return ResponseEntity.ok(orderService.finAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.finById(id));
    }

    @GetMapping("/search/{clientName}")
    public ResponseEntity<?> finByCLientName(@PathVariable String clientName){
        return ResponseEntity.ok(orderService.findByClientName(clientName));
    }

    @GetMapping("/search/{tableNumber}")
    public ResponseEntity<?> findByTableNumber(@PathVariable int tableNumber){
        return ResponseEntity.ok(orderService.findByTableNumber(tableNumber));
    }

    @GetMapping("/search/{waiterId}")
    public ResponseEntity<?> findByWaiterId(@PathVariable String waiterId){
        return ResponseEntity.ok(orderService.findByWaiterId(waiterId));
    }

    @GetMapping("/search/{date}")
    public ResponseEntity<?> findByDate(@PathVariable String date){
        return ResponseEntity.ok(orderService.findByDate(date));
    }

    @GetMapping("/search/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable String status){
        return ResponseEntity.ok(orderService.findByStatus(status));
    }




}
