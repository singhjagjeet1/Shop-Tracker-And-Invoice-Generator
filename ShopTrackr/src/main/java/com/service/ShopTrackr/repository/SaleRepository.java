package com.service.ShopTrackr.repository;

import com.service.ShopTrackr.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    @Query("Select s From Sale s Where " +
            ":customerName is null Or s.customerName = :customerName " +
            "AND (:startDate is null OR s.saleDate >= :startDate) " +
            "AND (:endDate is null OR s.saleDate <= :endDate) "+
            "AND (:customerContactNumber is null OR s.customerContactNumber = :customerContactNumber ) ")
    List<Sale> findSales(Date startDate, Date endDate, String customerName, String customerContactNumber);
}
