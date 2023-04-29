package com.etiya.ecommerce.services.concretes;

import com.etiya.ecommerce.core.exception.ServiceException;
import com.etiya.ecommerce.core.utils.mapping.ModelMapperService;
import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.core.utils.result.SuccessDataResult;
import com.etiya.ecommerce.entities.concrete.Customer;
import com.etiya.ecommerce.entities.concrete.Product;
import com.etiya.ecommerce.entities.concrete.ProductCategory;
import com.etiya.ecommerce.repositories.abstracts.IProductDao;
import com.etiya.ecommerce.services.abstracts.ProductService;
import com.etiya.ecommerce.services.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerce.services.dtos.requests.product.UpdateProductRequest;
import com.etiya.ecommerce.services.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerce.services.dtos.responses.product.GetByIdProductResponse;
import com.etiya.ecommerce.services.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerce.services.dtos.responses.product.UpdateProductResponse;
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
public class ProductManager implements ProductService {

    private IProductDao productDao;
    private ModelMapperService mapperService;
    private MessageSource messageSource;
    private ProductCategoryManager productCategoryManager;

    @Override
    public DataResult<GetByIdProductResponse> getById(Integer id) {
        Optional<Product> productOptional = productDao.findById(id);
        if (productOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        Product productToId = productOptional.get();
        GetByIdProductResponse response = mapperService.forResponse().map(productToId, GetByIdProductResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<AddProductResponse> add(AddProductRequest addProductRequest) {

        productCategoryManager.getProductCategoryId(addProductRequest.getCategoryId());
        Product product1 = new Product();
        product1 = mapperService.forRequest().map(addProductRequest, Product.class);
        productDao.save(product1);
        AddProductResponse addProductResponse = mapperService.forResponse().map(product1, AddProductResponse.class);
        return new SuccessDataResult<>(addProductResponse, "Ürün Eklendi");
    }

    @Override
    public DataResult<UpdateProductResponse> updateProduct(int id, UpdateProductRequest request) {

        Optional<Product> productOptional = productDao.findById(id);
        Product product =new Product();


        if(productOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id bulunamadı.", null, LocaleContextHolder.getLocale()));
        }

        product=mapperService.forResponse().map(request,Product.class);
        product.setId(id);
        productDao.save(product);

        UpdateProductResponse response = mapperService.forResponse().map(product, UpdateProductResponse.class);
        return new SuccessDataResult<>(response, "Ürün güncellendi.");
    }


    @Override
    public DataResult<List<ListProductResponse>> getAll() {
        List<Product> productsList = productDao.findAll();

        List<ListProductResponse> productResponses = productsList.
                stream().
                map(product -> {

                    ListProductResponse productResponse = mapperService.forResponse().map(product, ListProductResponse.class);
                    return productResponse;

                }).collect(Collectors.toList());

        return new SuccessDataResult<>(productResponses, "Ürünler listelendi");
    }

    protected Product productId(Integer productID) {
        Optional<Product> productOptional = productDao.findById(productID);
        if (productOptional.isEmpty()) {
            throw new ServiceException(messageSource.getMessage("Id Bulunamadı", null, LocaleContextHolder.getLocale()));
        }
        Product product = productOptional.get();
        return product;
    }
}
