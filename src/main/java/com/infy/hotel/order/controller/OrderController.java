package com.infy.hotel.order.controller;

import com.infy.hotel.order.dto.APIResponse;
import com.infy.hotel.order.dto.OrderRequest;
import com.infy.hotel.order.dto.OrderResponse;
import com.infy.hotel.order.model.Order;
import com.infy.hotel.order.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service")
@Slf4j
public class OrderController {

    private static final String SUCCESS = "success";

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<APIResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("order request : {}", orderRequest);
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        APIResponse apiResponse = APIResponse.builder()
                .results(orderResponse)
                .status(SUCCESS)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
