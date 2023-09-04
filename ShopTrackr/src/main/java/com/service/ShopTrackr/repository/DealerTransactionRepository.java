package com.service.ShopTrackr.repository;

import com.service.ShopTrackr.entity.DealerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DealerTransactionRepository extends JpaRepository<DealerTransaction,Long> {
    @Query("Select t From DealerTransaction t Where " +
            ":dealerName is null Or t.dealerName = :dealerName " +
            "AND (:startDate is null OR t.transactionDate >= :startDate) " +
            "AND (:endDate is null OR t.transactionDate <= :endDate) " )
    List<DealerTransaction> findTransactions(String dealerName, Date startDate, Date endDate);
}
