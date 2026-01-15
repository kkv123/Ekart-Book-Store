package com.ekart.bookStore.controller.customerdetails;

import com.ekart.bookStore.dto.CustomerDTO;
import com.ekart.bookStore.service.customerdetails.CustomerDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/e-kart/customerdetails")
public class CustomerDetailsController {

    CustomerDetailsServiceImp customerDetailsServiceImp;

    @Autowired
    public CustomerDetailsController(CustomerDetailsServiceImp customerDetailsServiceImp) {
        this.customerDetailsServiceImp = customerDetailsServiceImp;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerDTO customerDTO){
        customerDetailsServiceImp.newCustomer(customerDTO);
        return "Customer Added";
    }

    @PutMapping("/update/{id}")
        public String updateCustomer(@PathVariable long id, @RequestBody CustomerDTO customerDTO){
        customerDetailsServiceImp.updateCustomer(customerDTO,id);
        return "Customer Updated";
    }
}

