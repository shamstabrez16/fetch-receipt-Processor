package com.fetch.receiptProcessor.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "purchase_table")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "purchase_id", nullable = false)
    private Long id;
    @Column(name = "retailer", nullable = false)
    private String retailer;
    @Column(name = "purchaseDate", nullable = false)
    private String purchaseDate;
    @Column(name = "purchaseTime", nullable = false)
    private String purchaseTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchase")
    private List<ItemEntity> items;
    @Column(name = "total", nullable = false)
    private String total;
}
