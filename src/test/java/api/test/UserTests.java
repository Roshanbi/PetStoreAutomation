package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
    public Logger logger;// for logging msges
    
    
	@BeforeClass
	public void setup() {

		faker = new Faker();
		userPayload = new User();// object.

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		//Logs
		
		logger =LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("******creating user******");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******user is created******");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("******Reading user info******");

		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		//status code validation
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//ContentType validation
		 Assert.assertEquals(response.header("Content-Type"),"application/json");
		 
		 //Schema validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchema.json"));
		//extracted id
       int id= response.jsonPath().get("id");
       //extracted id matches 
       Assert.assertEquals(id,userPayload.getId());
       
       logger.info("******user info is displayed******");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		
		logger.info("******Updating user******");

		// updating user details
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());

		Response response = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().body();
		// response.then().log().body().statusCode(200);//chai assertion
		Assert.assertEquals(response.getStatusCode(), 200);// testNG assertion
		
		//checking data after update
		Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("******User is updated******");
		
	

	}
   
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("******Deleting user******");
    Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******User deleted******");
		
	}
}
