package com.fetch.receiptProcessor.Service;

import com.fetch.receiptProcessor.Entity.ItemEntity;
import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import com.fetch.receiptProcessor.Repository.ItemEntityRepository;
import com.fetch.receiptProcessor.Repository.PurchaseEntityRepository;
import com.fetch.receiptProcessor.dto.PointsDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class PurchaseService {

    @Autowired
    private PurchaseEntityRepository purchaseRepository;
    @Autowired
    private ItemEntityRepository itemEntityRepository;


    @Transactional
    public UUID processReceipt(PurchaseEntity purchaseEntity) {

        try {
            PurchaseEntity entity = PurchaseEntity.builder()
                    .retailer(purchaseEntity.getRetailer())
                    .purchaseDate(purchaseEntity.getPurchaseDate())
                    .purchaseTime(purchaseEntity.getPurchaseTime())
                    .total(purchaseEntity.getTotal())
                    .build();

            PurchaseEntity purchase = purchaseRepository.save(entity);

            List<ItemEntity> items = purchaseEntity.getItems().stream()
                    .map(itemEntity -> ItemEntity.builder()
                            .shortDescription(itemEntity.getShortDescription())
                            .price(itemEntity.getPrice())
                            .purchase(purchase)
                            .build())
                    .collect(Collectors.toList());

            itemEntityRepository.saveAll(items);
            return purchase.getId();
        } catch (Exception e) {
            // Handle the exception or rethrow it if needed
            throw e;
        }
    }

    public PointsDTO getPoints(UUID id) {
        CalculatorService calculator = new CalculatorService();
        Optional<PurchaseEntity> purchase = purchaseRepository.findById(id);
        return calculator.calculatePoints(purchase.orElseGet(null));

    }
    public  Optional<PurchaseEntity> getPurchase(UUID id) {
        Optional<PurchaseEntity> purchase = purchaseRepository.findById(id);
        return purchase;

    }

    public void setPurchaseRepository(PurchaseEntityRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
}
