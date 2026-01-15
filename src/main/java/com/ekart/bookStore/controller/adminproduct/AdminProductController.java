package com.ekart.bookStore.controller.adminproduct;


import com.ekart.bookStore.dto.ProductDTO;

import com.ekart.bookStore.service.adminproduct.AdminProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kart/adminproduct")
public class AdminProductController {

    AdminProductServiceImpl adminProductServiceImpl;

    @Autowired
    public AdminProductController(AdminProductServiceImpl adminProductServiceImpl) {
        this.adminProductServiceImpl = adminProductServiceImpl;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody List<ProductDTO> product){
        product.forEach(productDTO -> adminProductServiceImpl.newProduct(productDTO));
        return "Product Added";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable int id,@RequestBody ProductDTO productDTO){
        adminProductServiceImpl.updateProduct(productDTO,id);
        return "Product Updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        if(adminProductServiceImpl.removeProducts(id)){
            return "Product Deleted";
        }
        else{
            return "Product Don't Exist";
        }
    }
}
