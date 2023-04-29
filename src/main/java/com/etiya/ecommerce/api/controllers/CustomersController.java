package com.etiya.ecommerce.api.controllers;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.services.concretes.CustomerManager;
import com.etiya.ecommerce.services.dtos.requests.customer.AddCustomerRequest;
import com.etiya.ecommerce.services.dtos.responses.customer.AddCustomerResponse;
import com.etiya.ecommerce.services.dtos.responses.customer.GetByIdCustomerResponse;
import com.etiya.ecommerce.services.dtos.responses.customer.ListCustomerResponse;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersController {

    private CustomerManager customerManager;

    @GetMapping("")
    public DataResult<List<ListCustomerResponse>> getAll() {
        return customerManager.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetByIdCustomerResponse> getById(@PathVariable Integer id) {
        return customerManager.getById(id);
    }

    @PostMapping("")
    public DataResult<AddCustomerResponse> add(@Valid @RequestBody AddCustomerRequest request) {
        return customerManager.add(request);
    }


}
