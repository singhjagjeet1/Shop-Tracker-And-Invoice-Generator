package com.service.ShopTrackr.controller;

import com.service.ShopTrackr.entity.Claim;
import com.service.ShopTrackr.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ClaimController {
    @Autowired
    ClaimService claimService;
    @GetMapping("/getClaim")
    public Claim getClaim(@RequestParam String claimNumber) throws NoSuchFieldException {
        return claimService.getClaim(claimNumber);
    }

    @PostMapping("/saveClaim")
    public Claim saveClaim(@RequestBody Claim claim) throws NoSuchFieldException {
        return claimService.saveClaim(claim);
    }
    @PutMapping("/updateClaim")
    public Claim updateClaim(@RequestBody Claim claim) throws NoSuchFieldException {
        return claimService.updateClaim(claim);
    }
    @DeleteMapping("/deleteClaim")
    public void deleteClaim(@RequestParam long claimId) throws NoSuchFieldException {
        claimService.deleteClaim(claimId);
    }
    @GetMapping("/claimHistory")
    public List<Claim> claimHistory(@RequestParam(required = false) String customerName, @RequestParam(required = false) String productName, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) Date endDate, @RequestParam(required = false) String claimNumber, @RequestParam(required = false) String dealerName) throws NoSuchFieldException {
        List<Claim> claim = claimService.claimHistory(customerName,productName,startDate,endDate,claimNumber, dealerName);
        if(claim != null) {
            return claim;
        }
        else {
            throw new NoSuchFieldException("There is no such record exist");
        }
    }
}
