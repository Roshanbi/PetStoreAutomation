package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.payload.Pet.Category;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PetTests {
	Pet petPayload;
	Pet.Category category;
	Pet.Tag tag;
	public Logger logger;		//for logging messages

	@BeforeClass
	public void setUp() {

		// Create a Pet object

		category = new Pet.Category();
		category.setId(1);
		category.setName("Dog");

		tag = new Pet.Tag();
		tag.setId(1);
		tag.setName("Cutie");

		petPayload = new Pet();

		petPayload.setId(1234);
		petPayload.setCategory(category);
		petPayload.setName("Jimmy");

		List<String> photoUrls = Arrays.asList("photo");
		petPayload.setPhotoUrls(photoUrls);

		List<Pet.Tag> tags = Arrays.asList(tag);
		petPayload.setTags(tags);
		petPayload.setStatus("available");
		
		
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostPet() {

		logger.info("******creating pet******");
		Response response = PetEndPoints.createPet(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*****pet is created******");
	}

	@Test(priority = 2)
	public void testGetPetById() {

		logger.info("******ReadingPetInfo******");
		Response response = PetEndPoints.readPet(this.petPayload.getId());
		response.then().log().all();

		// Status code validation
		Assert.assertEquals(response.getStatusCode(), 200);

		// extracted id
		int id = response.jsonPath().get("id");
		Assert.assertEquals(id, petPayload.getId());

		// schema validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetSchema.json"));
		logger.info("******Pet info is displayed******");
	}

	@Test(priority = 3)
	public void testUpdatePet() {
		
		logger.info("******Updating pet******");
		// Update only specific fields in the payload
		
		petPayload.setName("Puppy");
		petPayload.setStatus("sold");

		 // Ensure the entire payload is sent
		Response response = PetEndPoints.updatePet(petPayload);

		response.then().log().body();
		// response.then().log().body().statusCode(200);//chai assertion
		Assert.assertEquals(response.getStatusCode(), 200);// testNG assertion

		Response responseAfterUpdate = PetEndPoints.readPet(this.petPayload.getId());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		 // Assert name and status after update
        String updatedName = responseAfterUpdate.jsonPath().get("name");
        String updatedStatus = responseAfterUpdate.jsonPath().get("status");
        
        Assert.assertEquals(updatedName, "Puppy");
        Assert.assertEquals(updatedStatus, "sold");
        logger.info("******updated pet info******");
	}

	@Test(priority = 4)
	public void testDeletePetById() {
		
		logger.info("******Deleteing pet******");

		Response response = PetEndPoints.deletePet(this.petPayload.getId());

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Deleted pet******");
	}

}
