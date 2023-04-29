package com.etiya.ecommerce.services.dtos.responses.customerOrderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddCustomerOrderProductResponse{

    private int id;

    private int quantity;

    private int orderId;

    private int productId;
}
