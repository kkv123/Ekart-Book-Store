package com.ekart.bookStore.service.product;

import com.ekart.bookStore.entity.product.Product;
import com.ekart.bookStore.repository.product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepo productRepository;

    @Autowired
    public ProductServiceImp(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingOrAuthorContaining(keyword, keyword);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
}
