package api.endpoints;

/* Swagger URI --> https://petstore.swagger.io/v2
 * 
 * Create user(post)-->https://petstore.swagger.io/v2/user
 * Get user(get)-->https://petstore.swagger.io/v2/user/{username}
 * Update user(put)--> https://petstore.swagger.io/v2/user/{username}
 * Delete user(delete)--> https://petstore.swagger.io/v2/user/{username}
 * 
 */

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";

	// User module
	
	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String put_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// store modules
	
	//store module url's here

	// pet modules
	
	/*
	 * Add a pet(post)-->https://petstore.swagger.io/v2/pet
	 * Get pet by ID(get)-->https://petstore.swagger.io/v2/pet/{petid}
	 * Update pet(put)-->https://petstore.swagger.io/v2/pet
	 * Delete pet(delete)--> https://petstore.swagger.io/v2/pet/{petid}
	 */
	
	public static final String post_pet_url = base_url + "/pet";
	public static final String get_pet_url = base_url + "/pet/{id}";
	public static final String put_pet_url = base_url + "/pet";
	public static final String delete_pet_url = base_url + "/pet/{id}";

}
