package com.service.ShopTrackr.repository;

import com.service.ShopTrackr.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,Long> {
    Optional<Dealer> findByDealerName(String dealerName);
}
