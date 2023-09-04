package com.service.ShopTrackr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "claims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long claimId;
    private Date startDate;
    private Date claimEndDate;
    private String dealerName;
    private String customerName;
    private String productName;
    private String claimNumber;

}
