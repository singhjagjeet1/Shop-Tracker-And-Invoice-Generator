package com.service.ShopTrackr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="dealer_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    private String dealerName;
    private long amountPaidByDealer;
    private long amountPaidToDealer;
    private Date transactionDate;
    private String description;
}
