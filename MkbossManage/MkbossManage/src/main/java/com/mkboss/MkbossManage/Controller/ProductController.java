package com.mkboss.MkbossManage.Controller;

import com.mkboss.MkbossManage.Entity.Product;
import com.mkboss.MkbossManage.Model.ProductModel;
import com.mkboss.MkbossManage.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/insert")
    public ResponseEntity<Product> insert(@RequestBody ProductModel productModel){
        var insertProduct = productService.addProduct(productModel);
        return ResponseEntity.ok(insertProduct);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestParam("id") long id, @RequestBody ProductModel productModel){
        var updateProduct = productService.updateProduct(id,productModel);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Product> delete(@RequestParam("id") long id){
        var deleteProduct = productService.deleteProduct(id);
        return ResponseEntity.ok(deleteProduct);
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(){
        var products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
}
