package com.service.ShopTrackr.controller;

import com.service.ShopTrackr.entity.Product;
import com.service.ShopTrackr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/saveProduct")
    public Product saveProduct(@RequestBody Product product) throws NoSuchFieldException {
        return productService.saveProduct(product);
    }
    @GetMapping("/getProduct")
    public Optional<Product> getProduct(@RequestParam long productId){
        return productService.getProduct(productId);
    }
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product updatedProduct) throws NoSuchFieldException {
        return productService.updateProduct(updatedProduct);
    }
    @DeleteMapping("/deleteProduct")
    public void deleteProduct(@RequestParam long productId) throws NoSuchFieldException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PutMapping("/addProductQuantity")
    public Product addProductQuantity(@RequestParam String productName, @RequestParam int addedQuantity) throws NoSuchFieldException {
        return productService.addProductQuantity(productName, addedQuantity);
    }
}
