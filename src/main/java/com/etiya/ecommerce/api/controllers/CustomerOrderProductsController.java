package com.etiya.ecommerce.api.controllers;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.services.concretes.CustomerOrderProductManager;
import com.etiya.ecommerce.services.dtos.requests.customerOrderProduct.AddCustomerOrderProductRequest;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.AddCustomerOrderProductResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.ListCustomerOrderProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer_product")
@AllArgsConstructor
public class CustomerOrderProductsController {

    private final CustomerOrderProductManager manager;

    @GetMapping("")
    public DataResult<List<ListCustomerOrderProductResponse>> getAll() {
        return manager.getAll();
    }

    @PostMapping("")
    public DataResult<AddCustomerOrderProductResponse> addCustomer(AddCustomerOrderProductRequest request) {
        return manager.add(request);
    }
}
