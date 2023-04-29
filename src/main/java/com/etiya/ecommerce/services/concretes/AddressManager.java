package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.Address;
import com.etiya.ecommerce.repositories.abstracts.IAddressDao;
import com.etiya.ecommerce.services.abstracts.AddressService;
import com.etiya.ecommerce.services.dtos.requests.address.AddAddressRequest;
import com.etiya.ecommerce.services.dtos.responses.address.AddAddressResponse;
import com.etiya.ecommerce.services.dtos.responses.address.GetByIdAddressResponse;
import com.etiya.ecommerce.services.dtos.responses.address.ListAddressResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressManager implements AddressService {

    private final IAddressDao addressDao;
    private ModelMapperService mapperService;
    private MessageSource messageSource;


    @Override
    public DataResult<GetByIdAddressResponse> addressGetById(Integer id) {

        Optional<Address> addressOptional = addressDao.findById(id);
        if (addressOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Aranılan id bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        Address addressToId = addressOptional.get();
        GetByIdAddressResponse response = mapperService.forResponse().map(addressToId, GetByIdAddressResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<AddAddressResponse> addAddress(AddAddressRequest addAddressRequest) {

        Address address = mapperService.forRequest().map(addAddressRequest, Address.class);
        addressDao.save(address);

        AddAddressResponse addressResponse = mapperService.forResponse().map(address, AddAddressResponse.class);
        return new SuccessDataResult<>(addressResponse, "adres eklendi");

    }

    @Override
    public DataResult<List<ListAddressResponse>> getAll() {

        List<ListAddressResponse> responses = addressDao.getAll();
        return new SuccessDataResult<>(responses, "adresler listelendi");
    }
}
