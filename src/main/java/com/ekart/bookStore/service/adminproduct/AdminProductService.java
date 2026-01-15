package com.ekart.bookStore.service.adminproduct;

import com.ekart.bookStore.dto.ProductDTO;

public interface AdminProductService {
    public void newProduct(ProductDTO productDTO);
    public void updateProduct(ProductDTO pro, int id);
    public Boolean removeProducts(int id);
}
