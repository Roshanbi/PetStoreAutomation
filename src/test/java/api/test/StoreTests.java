package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests {
	
	Store storePayload;
	Faker faker;
    Logger logger;
	@BeforeClass
	public void setUp() {
		
		
		//create store object
		storePayload =new Store();
		faker = new Faker();
		
		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setPetid(8);
		storePayload.setQuantity(2);
		storePayload.setShipDate("2024-12-15T10:20:05.124Z");
		storePayload.setStatus("placed");
		storePayload.setComplete(true);
		
		logger = LogManager.getLogger(this.getClass());
		
		}
	
	@Test(priority=1)
	public void testPostOrder() {
		logger.info("******Placing pet order******");
	Response response=	StoreEndPoints.placeOrderForPet(storePayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(),200);
	
	logger.info("*****order is created******");
		
	}
	
	@Test(priority=2)
	public void testGetPetById() {
		
		logger.info("******reading pet order******");
		
		 Response response=StoreEndPoints.readOrder(this.storePayload.getId());
		 response.then().log().all();
		int id= response.jsonPath().getInt("id");
		Assert.assertEquals(id, storePayload.getId());
		
		logger.info("******order info is displayed******");
	}
	
	@Test(priority=3)
	public void testDeleteOrderById() {
		
		logger.info("******order is deleting******");
		
	Response response=	StoreEndPoints.deleteOrder(this.storePayload.getId());
	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("******order deleted******");
		
	}
}
