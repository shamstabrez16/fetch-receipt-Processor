package com.fetch.receiptProcessor;

import com.fetch.receiptProcessor.Controller.ReceiptController;
import com.fetch.receiptProcessor.Entity.ItemEntity;
import com.fetch.receiptProcessor.Entity.PurchaseEntity;
import com.fetch.receiptProcessor.Repository.ItemEntityRepository;
import com.fetch.receiptProcessor.Repository.PurchaseEntityRepository;
import com.fetch.receiptProcessor.Service.PurchaseService;
import com.fetch.receiptProcessor.dto.PointsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class FetchReceiptProcessorApplicationTests {
	@Autowired
	private ReceiptController receiptController;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private PurchaseEntityRepository purchaseRepository;

	@Autowired
	private ItemEntityRepository itemEntityRepository;

	@PersistenceContext
	private EntityManager entityManager;


private UUID uuid;

	@Test
	@Transactional
	void testAddPurchase() {
		PurchaseEntity purchaseEntity = PurchaseEntity.builder()
				.retailer("Target")
				.purchaseDate("2022-01-01")
				.purchaseTime("13:01")
				.total(Double.parseDouble("35.35"))
				.items(Arrays.asList(
						ItemEntity.builder()
								.shortDescription("Mountain Dew 12PK")
								.price(Double.parseDouble("6.49"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Emils Cheese Pizza")
								.price(Double.parseDouble("12.25"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Knorr Creamy Chicken")
								.price(Double.parseDouble("1.26"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Doritos Nacho Cheese")
								.price(Double.parseDouble("3.35"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Klarbrunn 12-PK 12 FL OZ")
								.price(Double.parseDouble("12.00"))
								.build()
				))
				.build();

		UUID purchaseId = purchaseService.processReceipt(purchaseEntity);
		assertNotNull(purchaseId);
		PurchaseEntity savedPurchase = entityManager.find(PurchaseEntity.class, purchaseId);
		assertNotNull(savedPurchase);
		assertEquals(purchaseEntity.getRetailer(), savedPurchase.getRetailer());
		// Perform additional assertions as needed
		List<ItemEntity> savedItems = itemEntityRepository.findByPurchase(savedPurchase);
		assertEquals(5, savedItems.size());

		assertEquals(savedItems.get(3).getShortDescription(),"Doritos Nacho Cheese");
		// Perform additional assertions on the saved items
		// Clean up the test data
		purchaseRepository.deleteAllById(Collections.singleton(purchaseId));
		savedItems.forEach(item -> itemEntityRepository.deleteById(item.getId()));

	}

	@Test
	void getPoints() {
		// Create a mock PurchaseEntityRepository
		PurchaseEntityRepository purchaseRepository = mock(PurchaseEntityRepository.class);

		// Create an instance of the PurchaseService with the mock repository
		PurchaseService purchaseService = new PurchaseService();
		purchaseService.setPurchaseRepository(purchaseRepository);

		PurchaseEntity purchaseEntity = PurchaseEntity.builder()
				.retailer("Target")
				.purchaseDate("2022-01-01")
				.purchaseTime("13:01")
				.id(UUID.randomUUID())
				.total(Double.parseDouble("35.35"))
				.items(Arrays.asList(
						ItemEntity.builder()
								.shortDescription("Mountain Dew 12PK")
								.price(Double.parseDouble("6.49"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Emils Cheese Pizza")
								.price(Double.parseDouble("12.25"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Knorr Creamy Chicken")
								.price(Double.parseDouble("1.26"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Doritos Nacho Cheese")
								.price(Double.parseDouble("3.35"))
								.build(),
						ItemEntity.builder()
								.shortDescription("Klarbrunn 12-PK 12 FL OZ")
								.price(Double.parseDouble("12.00"))
								.build()
				))
				.build();


		// Set up the mock behavior of the PurchaseEntityRepository
		when(purchaseRepository.findById(purchaseEntity.getId())).thenReturn(Optional.of(purchaseEntity));

		// Invoke the getPoints method
		PointsDTO pointsDTO = purchaseService.getPoints(purchaseEntity.getId());

		// Verify that the PurchaseEntityRepository was called with the correct argument
		verify(purchaseRepository, times(1)).findById(purchaseEntity.getId());

		// Verify the result
		// Add assertions to verify the PointsDTO object based on the expected behavior
		// For example: assertEquals(expectedValue, pointsDTO.getPoints());
	}


}
