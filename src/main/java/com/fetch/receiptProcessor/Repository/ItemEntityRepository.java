package com.fetch.receiptProcessor.Repository;

import com.fetch.receiptProcessor.Entity.ItemEntity;
import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, UUID> {
    List<ItemEntity> findByPurchase(PurchaseEntity savedPurchase);
}