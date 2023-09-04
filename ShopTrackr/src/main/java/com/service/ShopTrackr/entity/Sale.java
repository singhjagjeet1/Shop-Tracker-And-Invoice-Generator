package com.service.ShopTrackr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    private long productId;
    private long customerId;
    private String productName;
    private String customerName;
    private Date saleDate;
    private Long salePrice;
    private Long saleQuantity;
    private Date warrantyStartDate;
    private Date warrantyEndDate;
    private String warrantyDurationInMonths;
    private String customerContactNumber;
    private boolean canClaim;
    private List<String> claim;
    private boolean needInvoice;
}
