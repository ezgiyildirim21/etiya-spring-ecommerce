package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.CustomerOrder;
import com.etiya.ecommerce.entities.concrete.CustomerOrderProduct;
import com.etiya.ecommerce.repositories.abstracts.CustomerOrderProductDao;
import com.etiya.ecommerce.services.abstracts.CustomerOrderProductService;
import com.etiya.ecommerce.services.dtos.requests.customerOrderProduct.AddCustomerOrderProductRequest;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.AddCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.AddCustomerOrderProductResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.ListCustomerOrderProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerOrderProductManager implements CustomerOrderProductService {

    private final CustomerOrderProductDao dao;
    private final ProductManager productManager;
    private ModelMapperService mapperService;
    private final CustomerOrderManager customerOrderManager;

    @Override
    public DataResult<List<ListCustomerOrderProductResponse>> getAll() {
        List<ListCustomerOrderProductResponse> responses = dao.getAll();
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<AddCustomerOrderProductResponse> add(AddCustomerOrderProductRequest addCustomerOrderProductRequest) {
        customerOrderManager.customerOrderId(addCustomerOrderProductRequest.getOrderId());
        productManager.productId(addCustomerOrderProductRequest.getProductId());

        CustomerOrderProduct customerOrderProduct = mapperService.forResponse()
                .map(addCustomerOrderProductRequest, CustomerOrderProduct.class);

        dao.save(customerOrderProduct);

        AddCustomerOrderProductResponse response = mapperService.forResponse()
                .map(customerOrderProduct, AddCustomerOrderProductResponse.class);

        return new SuccessDataResult<>(response);
    }
}
