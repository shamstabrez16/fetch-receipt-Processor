package com.fetch.receiptProcessor.Repository;

import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, UUID> {

}