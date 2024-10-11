package com.infy.hotel.order.dto;

import java.math.BigDecimal;

public class OrderLineItemsResponse {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
