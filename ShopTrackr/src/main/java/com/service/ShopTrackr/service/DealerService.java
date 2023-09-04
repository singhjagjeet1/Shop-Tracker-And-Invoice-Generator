package com.service.ShopTrackr.service;

import com.service.ShopTrackr.entity.Dealer;
import com.service.ShopTrackr.entity.DealerTransaction;
import com.service.ShopTrackr.entity.Purchase;
import com.service.ShopTrackr.repository.DealerRepository;
import com.service.ShopTrackr.repository.DealerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DealerService {
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    DealerTransactionRepository dealerTransactionRepository;
    public Dealer saveDealer(Dealer dealer) throws NoSuchFieldException {
        Optional<Dealer> optionalDealer = dealerRepository.findByDealerName(dealer.getDealerName());
        if(optionalDealer.isPresent())
        {
            throw new NoSuchFieldException("Already a Dealer is present with same name : "+dealer.getDealerName());
        }
        return dealerRepository.save(dealer);
    }

    public Optional<Dealer> getDealer(long dealerId) {
        return dealerRepository.findById(dealerId);
    }

    public Dealer updateDealer(Dealer updatedDealer) throws NoSuchFieldException {
        Optional<Dealer> dealer = dealerRepository.findById(updatedDealer.getDealerId());
        if (dealer.isPresent()) {
            return dealerRepository.save(updatedDealer);
        } else {
            throw new NoSuchFieldException("Dealer does not exist for DealerId: " + updatedDealer.getDealerId());
        }
    }

    public void deleteDealer(long dealerId) throws NoSuchFieldException {
        Optional<Dealer> dealer = dealerRepository.findById(dealerId);
        if (dealer.isPresent()) {
            dealerRepository.delete(dealer.get());
        } else {
            throw new NoSuchFieldException("Dealer does not exist for DealerId: " + dealerId);
        }
    }
    public ResponseEntity<Map<String, Object>> getDealerPurchaseHistory(String dealerName, Date startDate, Date endDate) throws NoSuchFieldException {
        if(dealerName!=null)
        {
          Optional<Dealer> dealer = dealerRepository.findByDealerName(dealerName);
          if(!dealer.isPresent())
          {
            throw new NoSuchFieldException("Dealer does not exist for DealerName: "+dealerName);
          }
        }
        List<Purchase> purchaseList = purchaseService.getPurchaseHistory(dealerName, startDate, endDate);
        Double totalPurchaseAmount = 0.0;
        for(Purchase purchase : purchaseList)
        {
            Double itemPrice = Double.valueOf(purchase.getPurchasePrice());
            Double itemCount = Double.valueOf(purchase.getPurchaseQuantity());
            totalPurchaseAmount= totalPurchaseAmount + itemCount*itemPrice;
        }
            Map<String, Object> response = new HashMap<>();
            response.put("TotalPurchaseAmount", totalPurchaseAmount);
            response.put("PurchaseDetails", purchaseList);
            return ResponseEntity.ok(response);
    }

    public Optional<Dealer> getDealerByDealerName(String dealerName) {
        return dealerRepository.findByDealerName(dealerName);
    }

    public DealerTransaction saveDealerTransaction(DealerTransaction dealerTransaction) throws NoSuchFieldException {
        Optional<Dealer> dealer = dealerRepository.findByDealerName(dealerTransaction.getDealerName());
        if(dealer.isPresent())
        {
            return dealerTransactionRepository.save(dealerTransaction);
        }
        else
        {
            throw new NoSuchFieldException("No Dealer found for dealerName: "+ dealerTransaction.getDealerName());
        }
    }

    public List<DealerTransaction> getDealerTransactions(String dealerName, Date startDate, Date endDate) throws NoSuchFieldException {
        if(dealerName!=null)
        {
            Optional<Dealer> dealer = dealerRepository.findByDealerName(dealerName);
            if(!dealer.isPresent())
            {
                throw new NoSuchFieldException("No such dealer found for dealerName: "+dealerName);
            }
        }
        List<DealerTransaction> dealerTransactions = dealerTransactionRepository.findTransactions(dealerName,startDate,endDate);
        return dealerTransactions;
    }

    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }
}
