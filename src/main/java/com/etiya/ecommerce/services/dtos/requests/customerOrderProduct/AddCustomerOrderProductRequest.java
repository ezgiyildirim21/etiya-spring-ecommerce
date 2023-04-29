package com.etiya.ecommerce.services.dtos.requests.customerOrderProduct;

import com.etiya.ecommerce.entities.concrete.CustomerOrder;
import com.etiya.ecommerce.entities.concrete.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddCustomerOrderProductRequest {

    private int quantity;

    private String comments;

    private int orderId;

    private int productId;

}
