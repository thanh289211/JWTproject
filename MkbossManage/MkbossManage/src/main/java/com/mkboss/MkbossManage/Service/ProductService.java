package com.mkboss.MkbossManage.Service;

import com.mkboss.MkbossManage.Model.ProductModel;

public interface ProductService {
    void addProduct(ProductModel productModel);
    void updateProduct(ProductModel productModel);
    void deleteProduct(ProductModel productModel);
    void getProducts();
}
