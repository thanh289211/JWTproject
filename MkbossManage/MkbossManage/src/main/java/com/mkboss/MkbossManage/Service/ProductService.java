package com.mkboss.MkbossManage.Service;

import com.mkboss.MkbossManage.Entity.Product;
import com.mkboss.MkbossManage.Model.ProductModel;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductModel productModel);
    Product updateProduct(long id, ProductModel productModel);
    Product deleteProduct(long id);
    List<Product> getProducts();
}
