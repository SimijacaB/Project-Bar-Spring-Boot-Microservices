package com.microservices.order.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "order_products")
    private List<OrderItem> orderProducts;

    @Column(name = "total_to_pay")
    private Double totalToPay;

    @Column(name = "waite_id")
    private String waiterId;

    private String notes;

    private StatusOrderItem status;

    private LocalDate date;

}
