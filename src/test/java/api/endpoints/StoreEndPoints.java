package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import io.restassured.response.Response;

public class StoreEndPoints {

	// CRUD operations

		public static Response placeOrderForPet(Store payload) {

			Response response = given()
					.contentType("application/json")
					.accept("application/json")
					.body(payload)

					.when().post(Routes.post_store_url);

			return response;

		}

		public static Response readOrder(int id) {

			Response response = given()
					.accept("application/json")
					.pathParam("orderid", id)

					.when()
					.get(Routes.get_store_url);

			return response;

		}
		public static Response deleteOrder(int id) {
			
			Response response=	given()
				 .pathParam("orderid",id)
				 .accept("application/json")
				 
				.when()
				 .delete(Routes.delete_store_url);
				return response;
				
				
				
			}

		}


