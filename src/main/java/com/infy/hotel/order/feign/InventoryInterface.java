package com.infy.hotel.order.feign;

import com.infy.hotel.order.dto.APIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("Inventory-Service")
public interface InventoryInterface {

    @GetMapping("/sku-code")
    ResponseEntity<APIResponse> inStock(@PathVariable("sku-code") String skuCode);

}
