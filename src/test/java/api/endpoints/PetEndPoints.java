package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Pet;
import io.restassured.response.Response;

public class PetEndPoints {
	
	//PetEndPoints created for CRUD implementation
	// Create a Pet (POST)
	public static Response createPet(Pet payload) {
		
		Response response =given()
		 .contentType("application/json")
		 .accept("application/json")
		 .body(payload)
		 
		.when()
		 .post(Routes.post_pet_url);
		
		return response;
		
	}
	
	// Read a Pet by ID (GET)
	public static Response readPet(int petId) {
		
		Response response=given()
		 .accept("application/json")
		 .pathParam("id", petId)
		 
		.when()
		 .get(Routes.get_pet_url);
		
		return response;
		
	}
	
	public static Response updatePet(Pet payload) {
	
		Response response=given()
		 
		 .accept("application/json")
		 .body(payload)
		
		
		.when()
		 .put(Routes.put_pet_url);
		
		
		return response;
		
	}
	
	public static Response deletePet(int petId) {
		
		Response response=given()
		 .accept("application/json")
		 .pathParam("id",petId)
		
		.when()
		 .delete(Routes.delete_pet_url);
		
		return response;
		
	}

}
