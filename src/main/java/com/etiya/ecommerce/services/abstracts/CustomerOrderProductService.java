package com.etiya.ecommerce.services.abstracts;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.services.dtos.requests.customerOrderProduct.AddCustomerOrderProductRequest;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.AddCustomerOrderProductResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.ListCustomerOrderProductResponse;

import java.util.List;

public interface CustomerOrderProductService {

    DataResult<List<ListCustomerOrderProductResponse>> getAll();

    DataResult<AddCustomerOrderProductResponse> add(AddCustomerOrderProductRequest addCustomerOrderProductRequest);

}
