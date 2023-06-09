package com.etiya.ecommerce.services.abstracts;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.services.dtos.requests.customerOrder.AddCustomerOrderRequest;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.AddCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.GetByIdCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.ListCustomerOrderResponse;
import jakarta.validation.Valid;


import java.util.List;

public interface CustomerOrderService {

    DataResult<AddCustomerOrderResponse> add(AddCustomerOrderRequest addCustomerOrderRequest);

    DataResult<GetByIdCustomerOrderResponse> getById(int id);

    DataResult<List<ListCustomerOrderResponse>> getAll();
}
