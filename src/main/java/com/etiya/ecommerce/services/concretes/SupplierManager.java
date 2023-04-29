package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.Supplier;
import com.etiya.ecommerce.repositories.abstracts.ISupplierDao;
import com.etiya.ecommerce.services.abstracts.SupplierService;
import com.etiya.ecommerce.services.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerce.services.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerce.services.dtos.responses.supplier.GetByIdSupplierResponse;
import com.etiya.ecommerce.services.dtos.responses.supplier.ListSupplierResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplierManager implements SupplierService {

    private final ISupplierDao supplierDao;
    private final ModelMapperService mapperService;
    private final MessageSource messageSource;

    @Override
    public DataResult<GetByIdSupplierResponse> getById(Integer id) {
        Optional<Supplier> supplierOptional = supplierDao.findById(id);
        if (supplierOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id Bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        Supplier supplierToId = supplierOptional.get();
        GetByIdSupplierResponse response = mapperService.forResponse().map(supplierToId, GetByIdSupplierResponse.class);

        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<AddSupplierResponse> add(AddSupplierRequest addSupplierRequest) {
        if (addSupplierRequest.getName().isEmpty() || addSupplierRequest.getSurname().isEmpty()) {
            throw new ServiceException("Supplier'ın Ad ve Soyad Alanı Boş Olamaz");
        }
        AddSupplierResponse response = mapperService.forResponse().map(addSupplierRequest, AddSupplierResponse.class);
        return new SuccessDataResult<>(response, "Tedarikçi Eklendi");
    }

    @Override
    public DataResult<List<ListSupplierResponse>> getAll() {
        List<ListSupplierResponse> responses = supplierDao.getAll();
        return new SuccessDataResult<>(responses);
    }
}
