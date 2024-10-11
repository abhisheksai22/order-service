package com.infy.hotel.order.service.impl;

import com.infy.hotel.order.dto.OrderLineItemsDto;
import com.infy.hotel.order.dto.OrderRequest;
import com.infy.hotel.order.dto.OrderResponse;
import com.infy.hotel.order.exception.CreateOrderException;
import com.infy.hotel.order.model.Order;
import com.infy.hotel.order.model.OrderLineItems;
import com.infy.hotel.order.repo.OrderRepository;
import com.infy.hotel.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        try {
            log.info("Converting request to entity, Request : {}",orderRequest );

            //Check if stock is available or not in inventory service


            Order order = orderRepository.save(createOrderFromRequest(orderRequest));
            log.info("converted successfully. Entity : {}",order.toString());
            OrderResponse orderResponse = createOrderResponse(order);
            log.info("Converted to order Response successfully");
            return orderResponse;
        } catch (Exception e) {
            throw new CreateOrderException("Order cannot be placed");
        }
    }

    private OrderResponse createOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItemsList(order.getOrderLineItemsList()
                        .stream()
                        .map(this::maptoOrderLineDTO)
                        .toList())
                .build();
    }

    private OrderLineItemsDto maptoOrderLineDTO(OrderLineItems orderLineItems) {
        OrderLineItemsDto orderLineItemsDto = new OrderLineItemsDto();
        orderLineItemsDto.setQuantity(orderLineItems.getQuantity());
        orderLineItemsDto.setPrice(orderLineItems.getPrice());
        orderLineItemsDto.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItemsDto;
    }

    private Order createOrderFromRequest(OrderRequest orderRequest) {
        return Order.builder()
                .orderLineItemsList(orderRequest.getOrderLineItemsList()
                        .stream()
                        .map(this::mapToOrderLine).toList())
                .orderNumber(UUID.randomUUID().toString())
                .build();
    }

    private OrderLineItems mapToOrderLine(OrderLineItemsDto orderLineItemDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemDTO.getQuantity());
        orderLineItems.setPrice(orderLineItemDTO.getPrice());
        orderLineItems.setSkuCode(orderLineItemDTO.getSkuCode());
        return orderLineItems;
    }

}
