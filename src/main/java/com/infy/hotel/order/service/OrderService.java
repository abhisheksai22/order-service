package com.infy.hotel.order.service;

import com.infy.hotel.order.dto.OrderRequest;
import com.infy.hotel.order.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);

}
