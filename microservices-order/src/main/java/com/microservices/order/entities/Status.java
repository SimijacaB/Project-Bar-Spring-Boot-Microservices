package com.microservices.order.entities;

public enum Status {


    CREATED,//  Cuando se crea la orden pero aún no se ha procesado.
    PROCESSING, // Cuando la orden está siendo procesada.
    PAID, //Cuando la orden ha sido pagada.
    PREPARING, // Cuando la orden está siendo preparada.
    READY, // Cuando la orden está lista para ser entregada o recogida.
    DELIVERED, // Cuando la orden ha sido entregada al cliente.
    CANCELLED; //Cuando la orden ha sido cancelada."

}
