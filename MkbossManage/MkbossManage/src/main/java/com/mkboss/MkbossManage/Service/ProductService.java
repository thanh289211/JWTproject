package com.mkboss.MkbossManage.Service;

import com.mkboss.MkbossManage.Model.ProductModel;

public interface ProductService {
    public void addProduct(ProductModel productModel);
    public void updateProduct(ProductModel productModel);
    public void deleteProduct(ProductModel productModel);
    public void getProducts();
}
