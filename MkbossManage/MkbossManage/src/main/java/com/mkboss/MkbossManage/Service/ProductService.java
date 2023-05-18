package com.mkboss.MkbossManage.Service;

import com.mkboss.MkbossManage.Entity.Product;
import com.mkboss.MkbossManage.Model.ProductModel;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductModel productModel);
    Product updateProduct(ProductModel productModel);
    Product deleteProduct(ProductModel productModel);
    List<Product> getProducts();
}
