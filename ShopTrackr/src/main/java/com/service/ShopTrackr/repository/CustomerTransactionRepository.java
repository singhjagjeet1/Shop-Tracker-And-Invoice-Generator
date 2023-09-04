package com.service.ShopTrackr.repository;

import com.service.ShopTrackr.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction,Long> {
    List<CustomerTransaction> findByCustomerName(String customerName);

    @Query("Select t From CustomerTransaction t Where " +
            ":customerName is null Or t.customerName = :customerName " +
            "AND (:startDate is null OR t.transactionDate >= :startDate) " +
            "AND (:endDate is null OR t.transactionDate <= :endDate) " )
    List<CustomerTransaction> findTransactions(String customerName, Date startDate, Date endDate);
}
