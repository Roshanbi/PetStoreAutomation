package api.test;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDPetTests {
	
	@Test(priority=1,dataProvider ="PetData",dataProviderClass=DataProviders.class)
	public void testCreatePet(String id, String categoryId,String categoryName,String name,String photoUrls,String tagId,String tagName,String status) {
		
		Pet petPayload = new Pet();
		
		petPayload.setId(Integer.parseInt(id));
		
		Pet.Category category= new Pet.Category();
		category.setId(Integer.parseInt(categoryId));
		category.setName(categoryName);
		
		petPayload.setCategory(category);
		petPayload.setName(name);
		petPayload.setPhotoUrls(Arrays.asList(photoUrls.split(",")));
		
		Pet.Tag tag=new Pet.Tag();
		tag.setId(Integer.parseInt(tagId));
		tag.setName(tagName);
		petPayload.setTags(Arrays.asList(tag));
		petPayload.setStatus(status);
		
		Response  response=PetEndPoints.createPet(petPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

@Test(priority=2, dataProvider="Ids",dataProviderClass=DataProviders.class)
	public void deletePet(String id) {
	
	Response response=PetEndPoints.deletePet(Integer.parseInt(id));
	Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
