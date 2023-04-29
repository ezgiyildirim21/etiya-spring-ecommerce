package com.etiya.ecommerce.api.controllers;

import com.etiya.ecommerce.core.utils.result.DataResult;
import com.etiya.ecommerce.services.concretes.ProductCategoryManager;
import com.etiya.ecommerce.services.dtos.requests.productCategory.AddProductCategoryRequest;
import com.etiya.ecommerce.services.dtos.requests.productCategory.UpdateProductCategoryRequest;
import com.etiya.ecommerce.services.dtos.responses.productCategory.AddProductCategoryResponse;
import com.etiya.ecommerce.services.dtos.responses.productCategory.ListProductCategoryResponse;
import com.etiya.ecommerce.services.dtos.responses.productCategory.UpdateProductCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class ProductsCategoriesController {

    private final ProductCategoryManager manager;

    @GetMapping("")
    public DataResult<List<ListProductCategoryResponse>> getAll() {
        return manager.getAll();
    }

    @PostMapping("")
    public DataResult<AddProductCategoryResponse> addCategory(@RequestBody AddProductCategoryRequest request) {
        return manager.add(request);
    }

    @PutMapping("{id}")
    public DataResult<UpdateProductCategoryResponse> updateProductCategory(@PathVariable Integer id,
                                                                           @RequestBody UpdateProductCategoryRequest request) {
        return manager.update(id, request);
    }
}
