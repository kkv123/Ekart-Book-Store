package com.ekart.bookStore.service.product;

import com.ekart.bookStore.entity.product.Product;
import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();
    public List<Product> searchProducts(String keyword);
    public Product getProductById(int id);

}
