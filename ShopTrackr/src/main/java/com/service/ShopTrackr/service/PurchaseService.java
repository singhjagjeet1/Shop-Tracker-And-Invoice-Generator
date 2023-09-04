package com.service.ShopTrackr.service;

import com.service.ShopTrackr.entity.Dealer;
import com.service.ShopTrackr.entity.Product;
import com.service.ShopTrackr.entity.Purchase;
import com.service.ShopTrackr.repository.DealerRepository;
import com.service.ShopTrackr.repository.ProductRepository;
import com.service.ShopTrackr.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DealerRepository dealerRepository;
    public Purchase savePurchase(Purchase purchase) throws NoSuchFieldException {
        String dealerName = purchase.getDealerName();
        Optional<Dealer> optionalDealer = dealerRepository.findByDealerName(dealerName);
        if(!optionalDealer.isPresent())
        {
            throw new NoSuchFieldException("No Such dealer with dealer name: " + dealerName);
        }
        String productName = purchase.getProductName();
        Optional<Product> optionalProduct = productRepository.findByProductName(productName);
        if (optionalProduct.isPresent())
        {
            Product product = optionalProduct.get();
            product.setProductQuantity(product.getProductQuantity() + purchase.getPurchaseQuantity());
            productRepository.save(product);
        }
        else
        {
            throw new NoSuchFieldException("No Such product with product name: " + productName);
        }
        return purchaseRepository.save(purchase);
    }

    public Optional<Purchase> getPurchase(long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }

    public Purchase updatePurchase(Purchase updatedPurchase) throws NoSuchFieldException {
        Optional<Purchase> purchase = purchaseRepository.findById(updatedPurchase.getPurchaseId());
        if (purchase.isPresent()) {
            return purchaseRepository.save(updatedPurchase);
        } else {
            throw new NoSuchFieldException("Purchase does not exist for PurchaseId: " + updatedPurchase.getPurchaseId());
        }
    }

    public void deletePurchase(long purchaseId) throws NoSuchFieldException {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
        if (purchase.isPresent()) {
            purchaseRepository.delete(purchase.get());
        } else {
            throw new NoSuchFieldException("Purchase does not exist for PurchaseId: " + purchaseId);
        }
    }

    public List<Purchase> getPurchaseHistory(String dealerName, Date startDate, Date endDate) {
        return purchaseRepository.findPurchases(startDate, endDate, dealerName);
    }


}
