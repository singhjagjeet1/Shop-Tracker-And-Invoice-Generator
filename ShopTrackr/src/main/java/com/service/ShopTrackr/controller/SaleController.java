package com.service.ShopTrackr.controller;

import com.service.ShopTrackr.entity.Sale;
import com.service.ShopTrackr.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SaleController {
    @Autowired
    SaleService saleService;
    @PostMapping("/saveSale")
    public Sale saveSale(@RequestBody Sale sale) throws NoSuchFieldException {
        return saleService.saveSale(sale);
    }
    @GetMapping("/getSale")
    public Optional<Sale> getSale(@RequestParam long saleId){
        return saleService.getSale(saleId);
    }
    @PutMapping("/updateSale")
    public Sale updateSale(@RequestBody Sale updatedSale) throws NoSuchFieldException {
        return saleService.updateSale(updatedSale);
    }
    @DeleteMapping("/deleteSale")
    public void deleteSale(@RequestParam long saleId) throws NoSuchFieldException {
        saleService.deleteSale(saleId);
    }
}
