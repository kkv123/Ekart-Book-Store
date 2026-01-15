package com.ekart.bookStore.controller.product;

import com.ekart.bookStore.entity.product.Product;
import com.ekart.bookStore.service.product.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kart/products")
public class ProductController {
//    @Autowired

    private ProductServiceImp productService;

    @Autowired
    public ProductController(ProductServiceImp productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> GetAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/key")
    public ResponseEntity<List<Product>> SearchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> GetProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
