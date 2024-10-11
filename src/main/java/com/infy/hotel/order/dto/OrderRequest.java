package com.infy.hotel.order.dto;

import com.infy.hotel.order.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsList;

}
