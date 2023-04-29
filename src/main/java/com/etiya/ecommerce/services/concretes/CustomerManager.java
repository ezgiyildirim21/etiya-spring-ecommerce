package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.Customer;
import com.etiya.ecommerce.repositories.abstracts.ICustomerDao;
import com.etiya.ecommerce.services.abstracts.CustomerService;
import com.etiya.ecommerce.services.dtos.requests.customer.AddCustomerRequest;
import com.etiya.ecommerce.services.dtos.responses.customer.AddCustomerResponse;
import com.etiya.ecommerce.services.dtos.responses.customer.GetByIdCustomerResponse;
import com.etiya.ecommerce.services.dtos.responses.customer.ListCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private ICustomerDao customerDao;
    private ModelMapperService mapperService;
    private MessageSource messageSource;

    @Override
    public DataResult<List<ListCustomerResponse>> getAll() {
        List<Customer> customersList = customerDao.findAll();

        List<ListCustomerResponse> customerResponses = customersList.
                stream().
                map(customer -> {

                    ListCustomerResponse customerResponse = mapperService.forResponse().map(customer, ListCustomerResponse.class);
                    return customerResponse;

                }).collect(Collectors.toList());

        return new SuccessDataResult<>(customerResponses, "Kullanıcılar listelendi");
    }

    @Override
    public DataResult<GetByIdCustomerResponse> getById(Integer id) {
        Optional<Customer> customerOptional = customerDao.findById(id);
        if (customerOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Aranılan id bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        Customer customerToId = customerOptional.get();
        GetByIdCustomerResponse response = mapperService.forResponse().map(customerToId, GetByIdCustomerResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<AddCustomerResponse> add(AddCustomerRequest addCustomerRequest) {
        Customer customer = new Customer();
        customer = mapperService.forRequest().map(addCustomerRequest, Customer.class);
        customerDao.save(customer);
        AddCustomerResponse addCustomerResponse = mapperService.forResponse().map(customer, AddCustomerResponse.class);
        return new SuccessDataResult<>(addCustomerResponse, "Müşteri Eklendi");
    }

    protected Customer customerId(Integer customer) {
        Optional<Customer> customerOptional = customerDao.findById(customer);
        if (customerOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("ID Bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        Customer customerr = customerOptional.get();
        return customerr;
    }

    /*
    customer.setFullName(addCustomerRequest.getFull_name());
        customer.setDetails(addCustomerRequest.getDetails());
        customer.setEmail(addCustomerRequest.getEmail());
        customer.setPhone(addCustomerRequest.getPhone());
     */

}
