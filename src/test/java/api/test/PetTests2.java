package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import api.endpoints.PetEndPoints2;
import api.payload.Pet;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

/*{
"id": 0,
"category": {
  "id": 0,
  "name": "string"
},
"name": "doggie",
"photoUrls": [
  "string"
],
"tags": [
  {
    "id": 0,
    "name": "string"
  }
],
"status": "available"
}*/
public class PetTests2 {
	Pet petPayload;
	Pet.Category category;
	Pet.Tag tag;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		category= new Pet.Category();
		category.setId(2);
		category.setName("Cat");
		
		tag=new Pet.Tag();
		tag.setId(2);
		tag.setName("Puppy");
		
		petPayload=new Pet();
		petPayload.setId(2345);
		petPayload.setCategory(category);
		petPayload.setName("Gimmy");
	List<String> photoUrls=	Arrays.asList("Photos");
	petPayload.setPhotoUrls(photoUrls);
	List<Pet.Tag>tags=Arrays.asList(tag);
	petPayload.setTags(tags);
	petPayload.setStatus("available");
	
	logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
	public void testPostPet() {
		logger.info("******adding a pet******");
		Response response=PetEndPoints2.createPet(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*****pet is added******");
		
	}
	@Test(priority=2)
	public void testGetPetById() {
		logger.info("******getting a pet by Id******");
	Response response=	PetEndPoints2.getPet(this.petPayload.getId());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);

	//validating id
	Assert.assertEquals(response.jsonPath().getInt("id"), petPayload.getId());
	
	//validating category
	
	Assert.assertEquals(response.jsonPath().getInt("category.id"), petPayload.getCategory().getId());
	Assert.assertEquals(response.jsonPath().getString("category.name"), petPayload.getCategory().getName());
	
	//validating name
	Assert.assertEquals(response.jsonPath().get("name"), petPayload.getName());
	//validating photoUrls
	Assert.assertEquals(response.jsonPath().getList("photoUrls"), petPayload.getPhotoUrls());
	//Assert.assertEquals(response.jsonPath().getString("photoUrls[0]"), petPayload.getPhotoUrls().get(0));
 //validating tags
Assert.assertEquals(response.jsonPath().getInt("tags[0].id"),petPayload.getTags().get(0).getId());
Assert.assertEquals(response.jsonPath().getString("tags[0].name"), petPayload.getTags().get(0).getName());
	
	//status validation
	Assert.assertEquals(response.jsonPath().getString("status"),petPayload.getStatus());
	
	//schema validation
	response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetSchema.json"));
	logger.info("******Pet info is displayed******");
	
	}
	@Test(priority=3)
	public void testUpdatePet() {
		logger.info("******Upadating existing pet details******");
		petPayload.getId();
		petPayload.setName("Minky");
		petPayload.setStatus("Sold");
		
	Response response =	PetEndPoints2.updatePet(petPayload);
	response.then().log().body();
	Assert.assertEquals(response.getStatusCode(), 200);
	
	
	logger.info("******Checking the updated details******");
	//checking updated details
	  Response responseAfterUpdate = PetEndPoints2.getPet(this.petPayload.getId());
	  Assert.assertEquals(responseAfterUpdate.jsonPath().get("name"), petPayload.getName());
	  Assert.assertEquals(responseAfterUpdate.jsonPath().get("status"), petPayload.getStatus());
	  logger.info("******Pet info updated******");
	
	}
	@Test(priority=4)
	public void testDeletePet() {
		 logger.info("******Deleting existing pet******");
	Response response =	PetEndPoints2.deletePet(this.petPayload.getId());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	
	 logger.info("******Deleted pet******");
	
		
		
	}
 
}
