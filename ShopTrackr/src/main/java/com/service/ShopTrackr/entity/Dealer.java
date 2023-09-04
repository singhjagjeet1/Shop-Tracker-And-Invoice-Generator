package com.service.ShopTrackr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="dealers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dealerId;
    private String dealerName;
    private String dealerContactNumber;
    private String dealerAddress;
    private String dealerAlternateContactNumber;
}
