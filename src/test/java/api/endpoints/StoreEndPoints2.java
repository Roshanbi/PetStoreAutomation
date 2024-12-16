package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.response.Response;

public class StoreEndPoints2 {
	
	 static ResourceBundle getURL() {
		
	ResourceBundle routes=	ResourceBundle.getBundle("routes");//loading routes
		
		
		return routes;
		
		}
	
	
	public static Response placeOrderForPet(Store payload) {
		
	String post_store_url = getURL().getString("post_store_url");
	
	Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
	
	.when()
	 .post(post_store_url);
	
	return response;
		
	}

public static Response readOrder(int id) {
	
	String get_store_url =getURL().getString("get_store_url");
	
	Response response =given()
	.accept("application/json") 
	.pathParam("orderid",id)
	
	.when()
	 .get(get_store_url);
	
	return response;
	
	
}

public static Response deleteOrder(int id) {
	
	String delete_store_url = getURL().getString("delete_store_url");
	
	Response response =given()
	.pathParam("orderid",id)
	 .accept("application/json")
	 
	.when()
	 .delete(delete_store_url);
	
	return response;
	
}
	
	}


