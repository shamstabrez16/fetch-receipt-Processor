package com.fetch.receiptProcessor.Repository;

import com.fetch.receiptProcessor.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {
}