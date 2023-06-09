package com.etiya.ecommerce.services.abstracts;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.entities.concrete.Supplier;
import com.etiya.ecommerce.services.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerce.services.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerce.services.dtos.responses.supplier.GetByIdSupplierResponse;
import com.etiya.ecommerce.services.dtos.responses.supplier.ListSupplierResponse;


import java.util.List;

public interface SupplierService {

    DataResult<AddSupplierResponse> add(AddSupplierRequest addSupplierRequest);

    DataResult<GetByIdSupplierResponse> getById(Integer id);

    DataResult<List<ListSupplierResponse>> getAll();
}
