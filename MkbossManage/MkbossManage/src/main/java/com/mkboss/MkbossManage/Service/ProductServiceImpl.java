package com.mkboss.MkbossManage.Service;

import com.mkboss.MkbossManage.Entity.Product;
import com.mkboss.MkbossManage.Model.ProductModel;
import com.mkboss.MkbossManage.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public Product addProduct(ProductModel productModel) {
        Product product = Product.builder()
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice())
                .stock(productModel.getStock())
                .build();
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(ProductModel productModel) {

    }

    @Override
    public Product deleteProduct(ProductModel productModel) {

    }

    @Override
    public List<Product> getProducts() {

    }
}
