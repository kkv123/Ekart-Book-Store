package com.ekart.bookStore.service.customerdetails;

import com.ekart.bookStore.dto.CustomerDTO;

public interface CustomerDetailsService {
    public void newCustomer(CustomerDTO customer);
    public void updateCustomer(CustomerDTO customer, long id);
}
