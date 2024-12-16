package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.endpoints.StoreEndPoints2;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests2 {
	
	Store storePayload;
	Faker faker;
	Logger logger;
	@BeforeClass
	public void setUp()
	{
		
		 storePayload =new Store();
		 faker =new Faker();
		 storePayload.setId(faker.idNumber().hashCode());
		 storePayload.setPetid(2);
		 storePayload.setQuantity(1);
		 storePayload.setShipDate("2024-12-15T10:20:05.124Z");
		 storePayload.setStatus("placed");
		 storePayload.setComplete(true);
		
		 logger = LogManager.getLogger(this.getClass());
	}	
		
	
	@Test(priority=1)
	public void testPostOrder() {
		logger.info("****Placing pet order****");
	Response response=	StoreEndPoints2.placeOrderForPet(storePayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("****Pet order placed****");
	}
	
	@Test(priority=2)
	public void testGetPetById() {
		logger.info("******reading pet order******");
		 Response response=StoreEndPoints2.readOrder(this.storePayload.getId());
		 response.then().log().all();
		int id= response.jsonPath().getInt("id");
		Assert.assertEquals(id, storePayload.getId());
		logger.info("******order info displayed******");
	}
	
	@Test(priority=3)
	public void testDeleteOrderById() {
		
		logger.info("******deleting pet order******");
		
	Response response=	StoreEndPoints2.deleteOrder(this.storePayload.getId());
	
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("******Deleted pet order******");
		
	}


}
