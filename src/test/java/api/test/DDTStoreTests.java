package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTStoreTests {

 
	
	@Test(priority=1,dataProvider="storeData", dataProviderClass=DataProviders.class)
	public void testPostOrder(String id,String petid, String quantity, String shipdate, String status, String complete) {
		
		Store storePayload = new Store();
		
		storePayload.setId(Integer.parseInt(id));
		storePayload.setPetid(Integer.parseInt(petid));
		storePayload.setQuantity(Integer.parseInt(quantity));
		storePayload.setShipDate(shipdate);
		storePayload.setStatus(status);
		storePayload.setComplete(Boolean.parseBoolean(complete));
		
		Response response=StoreEndPoints.placeOrderForPet(storePayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	
		
		
	}
	@Test(priority=2, dataProvider="ids", dataProviderClass=DataProviders.class)
	public void testDeleteByUsingId(String id) {
		
		Response response=StoreEndPoints.deleteOrder(Integer.parseInt(id));
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
