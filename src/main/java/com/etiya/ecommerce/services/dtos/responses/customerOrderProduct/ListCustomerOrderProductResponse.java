package com.etiya.ecommerce.services.dtos.responses.customerOrderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListCustomerOrderProductResponse {

    private int id;
    private int orderId;
    private int productId;

}
