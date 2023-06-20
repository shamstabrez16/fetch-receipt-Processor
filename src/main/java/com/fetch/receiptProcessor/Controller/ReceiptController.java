package com.fetch.receiptProcessor.Controller;


import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import com.fetch.receiptProcessor.Repository.PurchaseEntityRepository;
import com.fetch.receiptProcessor.Service.PurchaseService;
import com.fetch.receiptProcessor.dto.IdDTO;
import com.fetch.receiptProcessor.dto.PointsDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("receipt")
@RequiredArgsConstructor
public class ReceiptController {

    public  ReceiptController(PurchaseService purchaseService){
        this.purchaseService =purchaseService;
         }

    @Autowired
    private PurchaseService purchaseService;
    @PostMapping("/process")
    public ResponseEntity<IdDTO> addPurchase(@RequestBody @NonNull PurchaseEntity dto){
       return ResponseEntity.ok().body(IdDTO.builder().id(purchaseService.processReceipt(dto)).build());
    }


    @GetMapping("/{id}/points")
    public ResponseEntity<PointsDTO> getPoints(@PathVariable @NonNull UUID id){
        return ResponseEntity.ok().body(purchaseService.getPoints(id));
    }

}
