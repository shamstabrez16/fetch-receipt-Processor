package com.fetch.receiptProcessor;


import com.fetch.receiptProcessor.Entity.ItemEntity;
import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import com.fetch.receiptProcessor.Service.CalculatorService;
import com.fetch.receiptProcessor.dto.PointsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CalculatorClassTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testCountCharactersPositive() {
        String input = "Hello123";
        int count = calculatorService.countCharacters(input);
        Assertions.assertEquals(8, count);
    }
    @Test
    public void testCountCharactersNegative() {
        String input = "  Hello&&&1_2.3";
        int count = calculatorService.countCharacters(input);
        Assertions.assertEquals(8, count);
    }

    @Test
    public void testCalculateAlphanumericPoint() {
        String retailerName = "ABC123   ";
        int points = calculatorService.calculateAlphanumericPoint(retailerName);
        Assertions.assertEquals(6, points);
    }
    @Test
    public void testCalculateAlphanumericPointNegative() {
        String input = "  Hello&&&1_2.3";
        int points = calculatorService.calculateAlphanumericPoint(input);
        Assertions.assertEquals(8, points);
    }
    @Test
    public void testCalculateAlphanumericPointPositive() {
        String input = "Hello123";
        int points = calculatorService.calculateAlphanumericPoint(input);
        Assertions.assertEquals(8, points);
    }

    @Test
    public void testCalculateRoundedDollarAmountWithNoCents() {
        double total = 10.0;
        int points = calculatorService.calculateRoundedDollarAmountWithNoCents(total);
        Assertions.assertEquals(50, points);
    }

    @Test
    public void testCalculateMultipleOf025() {
        double total = 0.75;
        int points = calculatorService.calculateMultipleOf025(total);
        Assertions.assertEquals(25, points);
    }
    @Test
    public void testCalculateMultipleOf025WholeNumber() {
        double total = 1.00;
        int points = calculatorService.calculateMultipleOf025(total);
        Assertions.assertEquals(25, points);
    }
    @Test
    public void testCalculateMultipleOf025Negative() {
        double total = 1.05;
        int points = calculatorService.calculateMultipleOf025(total);
        Assertions.assertEquals(0, points);
    }

    @Test
    public void testCalculateEveryTwoItemsOnTheReceiptSizeOddNumber() {
        int size = 5;
        int points = calculatorService.calculateEveryTwoItemsOnTheReceipt(size);
        Assertions.assertEquals(10, points);
    }

    @Test
    public void testCalculateEveryTwoItemsOnTheReceiptSizeEvenNumber() {
        int size = 10;
        int points = calculatorService.calculateEveryTwoItemsOnTheReceipt(size);
        Assertions.assertEquals(25, points);
    }

    @Test
    public void testCalculateLengthOfTheItemDescription() {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setItems(new ArrayList<>());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Mountain Dew 12PK").price(6.49).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Emils Cheese Pizza").price(12.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Knorr Creamy Chicken").price(1.26).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Doritos Nacho Cheese").price(3.35).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("   Klarbrunn 12-PK 12 FL OZ  ").price(12.00).build());

        int points = calculatorService.calculateLengthOfTheItemDescription(purchaseEntity);
        Assertions.assertEquals(6 , points);
    }

    @Test
    public void testCalculateOnPurchaseDate() {
        String purchaseDate = "2023-06-01";
        int points = calculatorService.calculateOnPurchaseDate(purchaseDate);
        Assertions.assertEquals(6, points);
    }
    @Test
    public void testCalculateOnPurchaseDateNegative() {
        String purchaseDate = "2023-06-10";
        int points = calculatorService.calculateOnPurchaseDate(purchaseDate);
        Assertions.assertEquals(0, points);
    }

    @Test
    public void testCalculateTimeOfPurchase() {
        String purchaseTime = "15:30";
        int points = calculatorService.calculateTimeOfPurchase(purchaseTime);
        Assertions.assertEquals(10, points);
    }
    @Test
    public void testCalculateTimeOfPurchaseNegative() {
        String purchaseTime = "03:30";
        int points = calculatorService.calculateTimeOfPurchase(purchaseTime);
        Assertions.assertEquals(0, points);
    }

    @Test
    public void testCalculatePoints() {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setItems(new ArrayList<>());
        purchaseEntity.setRetailer("Target");
        purchaseEntity.setTotal(35.35);
        purchaseEntity.setPurchaseDate("2022-01-01");
        purchaseEntity.setPurchaseTime("13:01");
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Mountain Dew 12PK").price(6.49).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Emils Cheese Pizza").price(12.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Knorr Creamy Chicken").price(1.26).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Doritos Nacho Cheese").price(3.35).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("   Klarbrunn 12-PK 12 FL OZ  ").price(12.00).build());

        PointsDTO pointsDTO = calculatorService.calculatePoints(purchaseEntity);
        Assertions.assertEquals(28, pointsDTO.getPoints());
    }

    @Test
    public void testCalculatePoints2nd() {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setItems(new ArrayList<>());
        purchaseEntity.setRetailer("M&M Corner Market");
        purchaseEntity.setTotal(9.00);
        purchaseEntity.setPurchaseDate("2022-03-20");
        purchaseEntity.setPurchaseTime("14:33");
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        PointsDTO pointsDTO = calculatorService.calculatePoints(purchaseEntity);
        Assertions.assertEquals(109, pointsDTO.getPoints());
    }
}
