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
    public Product updateProduct(long id, ProductModel productModel) {
        if (productModel.getId() != id){
            return null;
        }
        var product = productRepository.findById(id).orElseThrow();
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setPrice(productModel.getPrice());
        product.setStock(productModel.getStock());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(long id) {
        var product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
