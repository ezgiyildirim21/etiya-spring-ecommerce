package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.Customer;
import com.etiya.ecommerce.entities.concrete.Product;
import com.etiya.ecommerce.repositories.abstracts.CustomerOrderDao;
import com.etiya.ecommerce.services.abstracts.CustomerOrderService;
import com.etiya.ecommerce.entities.concrete.CustomerOrder;
import com.etiya.ecommerce.services.dtos.requests.customerOrder.AddCustomerOrderRequest;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.AddCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.GetByIdCustomerOrderResponse;
import com.etiya.ecommerce.services.dtos.responses.customerOrder.ListCustomerOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerOrderManager implements CustomerOrderService {

    private CustomerOrderDao customerOrderDao;

    private ModelMapperService modelMapperService;

    private CustomerManager customerManager;
    private MessageSource messageSource;


    @Override
    public DataResult<AddCustomerOrderResponse> add(AddCustomerOrderRequest addCustomerOrderRequest) {

        Customer customer = customerManager.customerId(addCustomerOrderRequest.getCustomerId());

        CustomerOrder customerOrder = modelMapperService.forRequest().map(addCustomerOrderRequest, CustomerOrder.class);
        CustomerOrder saveCustomerOrder = customerOrderDao.save(customerOrder);

        AddCustomerOrderResponse response = modelMapperService.forResponse().map(saveCustomerOrder, AddCustomerOrderResponse.class);


        BeanUtils.copyProperties(customer, response);


        return new SuccessDataResult<>(response);

/*
        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setPlaced(addCustomerOrderRequest.getPlaced());
        customerOrder.setDetails(addCustomerOrderRequest.getDetails());
        customerOrder.setStatusCode(addCustomerOrderRequest.getStatusCode());
        customerOrder.setPaid(addCustomerOrderRequest.getPaid());
        customerOrder.setPrice(addCustomerOrderRequest.getPrice());

        customerOrderDao.save(customerOrder);

        AddCustomerOrderResponse response=new AddCustomerOrderResponse();


        response.setId(customerOrder.getId());
        response.setPaid(customerOrder.getPaid());
        response.setPrice(customerOrder.getPrice());
        response.setPlaced(customerOrder.getPlaced());
        response.setStatusCode(customerOrder.getStatusCode());
        response.setCustomerId(customerOrder.getCustomer().getId());
        response.setFullName(customerOrder.getCustomer().getFullName());


        return response;


 */

    }


    @Override
    public DataResult<List<ListCustomerOrderResponse>> getAll() {
        List<CustomerOrder> customerOrder = customerOrderDao.findAll();

        List<ListCustomerOrderResponse> responses = customerOrder.stream().map(customerOrder1 -> {
            ListCustomerOrderResponse customerOrder2 = modelMapperService.forResponse().
                    map(customerOrder1, ListCustomerOrderResponse.class);
            return customerOrder2;
        }).collect(Collectors.toList());

        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetByIdCustomerOrderResponse> getById(int id) {


        Optional<CustomerOrder> customerOrderOptional = customerOrderDao.findById(id);
        if (customerOrderOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id Bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        CustomerOrder customerOrder = customerOrderOptional.get();
        GetByIdCustomerOrderResponse response = modelMapperService.forResponse().map(customerOrder, GetByIdCustomerOrderResponse.class);
        return new SuccessDataResult<>(response);
    }

    protected CustomerOrder customerOrderId(Integer id) {
        Optional<CustomerOrder> customerOrderOptional = customerOrderDao.findById(id);
        if (customerOrderOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        CustomerOrder customerOrder = customerOrderOptional.get();
        return customerOrder;
    }

}


