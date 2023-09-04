package com.service.ShopTrackr.service;

import com.service.ShopTrackr.entity.Claim;
import com.service.ShopTrackr.entity.Customer;
import com.service.ShopTrackr.entity.Dealer;
import com.service.ShopTrackr.entity.Product;
import com.service.ShopTrackr.repository.ClaimRepository;
import com.service.ShopTrackr.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    DealerService dealerService;

    public Claim getClaim(String claimNumber) throws NoSuchFieldException {
        Optional<Claim> claim = claimRepository.findByClaimNumber(claimNumber);
        if(claim.isPresent()) {
            return claim.get();
        }
        else {
            throw new NoSuchFieldException(String.format("There is no such Claim for Claim number: %s",claimNumber));
        }
    }

    public Claim saveClaim(Claim claim) throws NoSuchFieldException {
      String customerName = claim.getCustomerName();
      Optional<Customer> optionalCustomer = customerRepository.findByCustomerName(customerName);
      if(!optionalCustomer.isPresent())
      {
          throw new NoSuchFieldException("There is no sale by customerName : " + customerName);
      }
       return claimRepository.save(claim);
    }

    public Claim updateClaim(Claim updatedClaim) throws NoSuchFieldException {
        Optional<Claim> claim = claimRepository.findById(updatedClaim.getClaimId());
        if(claim.isPresent())
        {
            return claimRepository.save(updatedClaim);
        }
        else{
            throw new NoSuchFieldException("No such claim is found for claim:"+updatedClaim);
        }
    }

    public void deleteClaim(long claimId) throws NoSuchFieldException {
        Optional<Claim> claim = claimRepository.findById(claimId);
        if(claim.isPresent()){
            claimRepository.delete(claim.get());
        }
        else{
            throw new NoSuchFieldException("No such claim found for claimId:"+claimId);
        }
    }

    public List<Claim> claimHistory(String customerName, String productName, Date startDate, Date endDate, String claimNumber, String dealerName) throws NoSuchFieldException {
        if(claimNumber!=null) {
            Optional<Claim> claim = claimRepository.findByClaimNumber(claimNumber);
            if (!claim.isPresent()) {
                throw new NoSuchFieldException("No such claim found for claimId: " + claimNumber);
            }
        }
        if(customerName!=null) {
            Optional<Customer> customer = customerService.getCustomerByCustomerName(customerName);
            if(!customer.isPresent()) {
                throw new NoSuchFieldException("No Such Customer with customer Name: "+customerName);
            }
        }
        if(dealerName!=null) {
            Optional<Dealer> dealer = dealerService.getDealerByDealerName(dealerName);
            if(!dealer.isPresent()) {
                throw new NoSuchFieldException("No Such dealer with dealer Name: "+dealerName);
            }
        }
        if(productName!=null) {
            Optional<Product> product = productService.getProductByProductName(productName);
            if(!product.isPresent()) {
                throw new NoSuchFieldException("No Such product with product Name: "+productName);
            }
        }
        List<Claim> claims = claimRepository.findClaims(claimNumber, startDate, endDate, customerName, productName, dealerName);
        return claims;
    }
}
