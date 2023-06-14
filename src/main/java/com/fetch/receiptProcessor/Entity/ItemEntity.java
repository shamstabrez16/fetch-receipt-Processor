package com.fetch.receiptProcessor.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "item_table")
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id", nullable = false)
    private Long id;
    @Column(name = "shortDescription", nullable = false)
    private String shortDescription;
    @Column(name = "price", nullable = false)
    private String price;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;
}
