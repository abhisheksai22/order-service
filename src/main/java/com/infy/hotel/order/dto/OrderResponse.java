package com.infy.hotel.order.dto;

import com.infy.hotel.order.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItemsList;
}
