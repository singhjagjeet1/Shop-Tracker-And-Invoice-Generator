package com.service.ShopTrackr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name ="purchases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;
    private long dealerId;
    private long productId;
    private long purchasePrice;
    private Date purchaseDate;
    private long purchaseQuantity;
    private String productName;
    private String dealerName;
}
