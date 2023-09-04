package com.service.ShopTrackr.repository;

import com.service.ShopTrackr.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    @Query("Select p From Purchase p Where " +
            ":dealerName is null Or p.dealerName = :dealerName " +
            "AND (:startDate is null OR p.purchaseDate >= :startDate) " +
            "AND (:endDate is null OR p.purchaseDate <= :endDate) ")
    List<Purchase> findPurchases(Date startDate, Date endDate, String dealerName);
}
