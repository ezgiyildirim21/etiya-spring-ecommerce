package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.ProductCategory;
import com.etiya.ecommerce.repositories.abstracts.IProductCategoryDao;
import com.etiya.ecommerce.services.abstracts.ProductCategoryService;
import com.etiya.ecommerce.services.dtos.requests.productCategory.AddProductCategoryRequest;
import com.etiya.ecommerce.services.dtos.requests.productCategory.UpdateProductCategoryRequest;
import com.etiya.ecommerce.services.dtos.responses.productCategory.AddProductCategoryResponse;
import com.etiya.ecommerce.services.dtos.responses.productCategory.ListProductCategoryResponse;
import com.etiya.ecommerce.services.dtos.responses.productCategory.UpdateProductCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductCategoryManager implements ProductCategoryService {

    private final IProductCategoryDao dao;
    private MessageSource messageSource;
    private ModelMapperService mapperService;


    @Override
    public DataResult<List<ListProductCategoryResponse>> getAll() {
        List<ProductCategory> productCategoryList = dao.findAllByProductCategory();

        List<ListProductCategoryResponse> productCategoryResponses = productCategoryList.
                stream().
                map(productCategory -> {
                    ListProductCategoryResponse response = mapperService.forResponse().map(productCategory, ListProductCategoryResponse.class);
                    return response;
                }).collect(Collectors.toList());

        return new SuccessDataResult<>(productCategoryResponses, "Kategoriler listelendi.");
    }

    @Override
    public DataResult<AddProductCategoryResponse> add(AddProductCategoryRequest addProductCategoryRequest) {
        ProductCategory productCategory = new ProductCategory();
        productCategory = mapperService.forRequest().map(addProductCategoryRequest, ProductCategory.class);
        dao.save(productCategory);

        AddProductCategoryResponse addProductCategoryResponse = mapperService.forResponse().map(productCategory, AddProductCategoryResponse.class);
        return new SuccessDataResult<>(addProductCategoryResponse, "Kategori eklendi.");
    }

    @Override
    public DataResult<UpdateProductCategoryResponse> update(int id, UpdateProductCategoryRequest request) {

        Optional<ProductCategory> productCategoryOptional = dao.findById(id);


        if (productCategoryOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        request.setId(id);

        ProductCategory productCategory =new ProductCategory();
        BeanUtils.copyProperties(request,productCategory);

        dao.save(productCategory);

        UpdateProductCategoryResponse response = mapperService.forResponse().map(productCategory, UpdateProductCategoryResponse.class);
        return new SuccessDataResult<>(response, "Kategori güncellendi.");

    }


    protected ProductCategory getProductCategoryId(Integer id) {

        Optional<ProductCategory> productCategoryOptional = dao.findById(id);

        if (productCategoryOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        ProductCategory productCategory = productCategoryOptional.get();
        return productCategory;
    }
}
