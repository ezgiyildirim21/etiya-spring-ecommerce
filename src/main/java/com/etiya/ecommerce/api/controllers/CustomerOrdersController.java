package com.etiya.ecommerce.api.controllers;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.services.concretes.CustomerOrderManager;
import com.etiya.ecommerce.services.dtos.requests.customerOrder.AddCustomerOrderRequest;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.AddCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.GetByIdCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.ListCustomerOrderResponse;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customerOrders")
@AllArgsConstructor
public class CustomerOrdersController {

    private CustomerOrderManager customerOrderManager;

    @GetMapping("")
    public DataResult<List<ListCustomerOrderResponse>> getAll() {

        return customerOrderManager.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetByIdCustomerOrderResponse> getById(@PathVariable int id) {

        return customerOrderManager.getById(id);
    }

    @PostMapping("/save")
    public DataResult<AddCustomerOrderResponse> add(@RequestBody AddCustomerOrderRequest addCustomerOrderRequest) {
        return customerOrderManager.add(addCustomerOrderRequest);
    }
}
