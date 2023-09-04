package com.service.ShopTrackr.repository;

import com.service.ShopTrackr.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
    Optional<Claim> findByClaimNumber(String claimNumber);

    @Query("SELECT c FROM Claim c WHERE " +
            "(:claimNumber is null OR c.claimNumber = :claimNumber) " +
            "AND (:startDate is null OR c.startDate >= :startDate) " +
            "AND (:endDate is null OR c.startDate <= :endDate) " +
            "AND (:customerName is null OR c.customerName = :customerName) " +
            "AND (:productName is null OR c.productName = :productName) "  +
            "AND (:dealerName is null OR c.dealerName = :dealerName) ")
    List<Claim> findClaims(String claimNumber, Date startDate, Date endDate, String customerName, String productName, String dealerName);
}
