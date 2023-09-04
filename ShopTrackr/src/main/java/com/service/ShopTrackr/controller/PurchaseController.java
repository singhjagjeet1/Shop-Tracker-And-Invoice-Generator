package com.service.ShopTrackr.controller;

import com.service.ShopTrackr.entity.Purchase;
import com.service.ShopTrackr.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;
    @PostMapping("/savePurchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) throws NoSuchFieldException {
        return purchaseService.savePurchase(purchase);
    }
    @GetMapping("/getPurchase")
    public Optional<Purchase> getPurchase(@RequestParam long purchaseId){
        return purchaseService.getPurchase(purchaseId);
    }
    @PutMapping("/updatePurchase")
    public Purchase updatePurchase(@RequestBody Purchase updatedPurchase) throws NoSuchFieldException {
        return purchaseService.updatePurchase(updatedPurchase);
    }
    @DeleteMapping("/deletePurchase")
    public void deletePurchase(@RequestParam long purchaseId) throws NoSuchFieldException
    {
        purchaseService.deletePurchase(purchaseId);
    }


}
