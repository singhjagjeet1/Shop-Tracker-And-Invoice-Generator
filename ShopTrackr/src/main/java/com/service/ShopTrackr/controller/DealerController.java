package com.service.ShopTrackr.controller;

import com.service.ShopTrackr.entity.Dealer;
import com.service.ShopTrackr.entity.DealerTransaction;
import com.service.ShopTrackr.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DealerController {
    @Autowired
    DealerService dealerService;
    @PostMapping("/saveDealer")
    public Dealer saveDealer(@RequestBody Dealer dealer) throws NoSuchFieldException {
        return dealerService.saveDealer(dealer);
    }
    @GetMapping("/getDealer")
    public Optional<Dealer> getDealer(@RequestParam long dealerId){
        return dealerService.getDealer(dealerId);
    }
    @PutMapping("/updateDealer")
    public Dealer updateDealer(@RequestBody Dealer updatedDealer) throws NoSuchFieldException {
        return dealerService.updateDealer(updatedDealer);
    }
    @DeleteMapping("/deleteDealer")
    public void deleteDealer(@RequestParam long dealerId) throws NoSuchFieldException {
        dealerService.deleteDealer(dealerId);
    }

    @GetMapping("/dealerPurchaseHistory")
    public ResponseEntity<Map<String, Object>> getDealerPurchaseHistory(@RequestParam(required = false) String dealerName, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date endDate) throws NoSuchFieldException {
        return dealerService.getDealerPurchaseHistory(dealerName, startDate, endDate);
    }
    @PostMapping("/saveDealerTransaction")
    public DealerTransaction saveDealerTransaction(@RequestBody DealerTransaction dealerTransaction) throws NoSuchFieldException {
       return dealerService.saveDealerTransaction(dealerTransaction);
    }
    @GetMapping("/getDealerTransactions")
    public List<DealerTransaction> getDealerTransactions(@RequestParam(required = false) String dealerName, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date endDate) throws NoSuchFieldException {
          return dealerService.getDealerTransactions(dealerName,startDate,endDate);
    }

    @GetMapping("/getAllDealers")
    public List<Dealer> getAllDealers() {
        return dealerService.getAllDealers();
    }
}
