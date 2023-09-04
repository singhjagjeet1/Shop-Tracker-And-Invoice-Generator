package com.service.ShopTrackr.service;

import com.service.ShopTrackr.entity.Product;
import com.service.ShopTrackr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product) throws NoSuchFieldException {
        Optional<Product> optionalProduct = productRepository.findByProductName(product.getProductName());
        if(optionalProduct.isPresent())
        {
            throw new NoSuchFieldException("Already a product is present with same name : "+product.getProductName());
        }
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(long productId) {
        return productRepository.findById(productId);
    }

    public Product updateProduct(Product updatedProduct) throws NoSuchFieldException {
        Optional<Product> product = productRepository.findById(updatedProduct.getProductId());
        if (product.isPresent()) {
            return productRepository.save(updatedProduct);
        } else {
            throw new NoSuchFieldException("Product does not exist for ProductId: " + updatedProduct.getProductId());
        }
    }

    public void deleteProduct(long productId) throws NoSuchFieldException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        } else {
            throw new NoSuchFieldException("Product does not exist for ProductId: " + productId);
        }
    }

    public Optional<Product> getProductByProductName(String productName) {
        Optional<Product> product = productRepository.findByProductName(productName);
        return  product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProductQuantity(String productName, int addedQuantity) throws NoSuchFieldException {
        Optional<Product> optionalProduct = productRepository.findByProductName(productName);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductQuantity(product.getProductQuantity()+addedQuantity);
            return productRepository.save(product);
        } else {
            throw new NoSuchFieldException("No Such product with product name: "+ productName);
        }
    }
}
