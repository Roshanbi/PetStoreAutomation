package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;

//UserEndPoints.java created for perform CRUD operations

public class UserEndpoints {

	public static Response createUser(User payload) {

		Response response = given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload)

				.when()
				 .post(Routes.post_url);//add ; to store response in variable

		return response;
	}

 public static Response readUser(String userName)
{
Response response=given()
			  .pathParam("username", userName)
	
	        .when()
	         .get(Routes.get_url);
return response;

}

 public static Response updateUser(User payload , String userName)
 {
	Response response= given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
			.pathParam("username", userName)
	 .when()
	  .put(Routes.put_url);
	return response;
 }
 
 public static Response deleteUser(String userName)
 {
	Response response= given()
	                    .pathParam("username", userName)
	            .when()
	             .delete(Routes.delete_url);
	return response;
 }

}
