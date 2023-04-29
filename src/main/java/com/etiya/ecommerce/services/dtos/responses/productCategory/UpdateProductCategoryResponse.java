package com.etiya.ecommerce.services.dtos.responses.productCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductCategoryResponse {

    private int productCategoryId;
    private String name;
}
