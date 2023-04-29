package com.etiya.ecommerce.repositories.abstracts;

import com.etiya.ecommerce.entities.concrete.CustomerOrderProduct;
import com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.ListCustomerOrderProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderProductDao extends JpaRepository<CustomerOrderProduct, Integer> {

    @Query("select new com.etiya.ecommerce.services.dtos.responses.customerOrderProduct.ListCustomerOrderProductResponse(co.id,co.product.id,co.order.customerOrderId) from CustomerOrderProduct co")
    List<ListCustomerOrderProductResponse> getAll();
}
