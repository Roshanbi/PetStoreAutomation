package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.response.Response;

public class PetEndPoints2 {

	static ResourceBundle getURL() {

		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;

	}

	 public static Response createPet(Pet payload) {

		String post_pet_url = getURL().getString("post_pet_url");

		Response response = given()
				            .contentType("application/json")
				            .accept("application/json").body(payload)

				           .when()
				           .post(post_pet_url);
		return response;
	}

	public static Response getPet(int petId) {
		String get_pet_url = getURL().getString("get_pet_url");

		Response response = given()
				.pathParam("id", petId)
				.accept("application/json")

				.when()
				 .get(get_pet_url);

		return response;

	}
	
	public static Response updatePet(Pet payload) {
		
	String update_pet_url =	getURL().getString("update_pet_url");
		
		Response response = given()
		                     .accept("application/json")
		                     .body(payload)
		                     .contentType("application/json")
		                     
		                     .when()
		                      .put(update_pet_url);
		return response;
		
	}
  
	public static Response deletePet(int petId) {
		
	String	delete_pet_Url	=getURL().getString("delete_pet_Url");
		
		Response response = (Response) given()
				             .accept("application/json")
				             .pathParam("id",petId)
				             
				           .when()
				            .delete(delete_pet_Url);
		return response;
		
		
	}
}
