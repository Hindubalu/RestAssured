package ra.day1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.HashMap;


/*
 * Gherkin  Keywords:
 * given()-prerequisite [Headers, contentType, request body, authentication, query parameters , path parameters]
 * when()-request Methods [GET,POST,PUT,PATCH,DELETE]
 * then()-validation [statusCode, response body, response time, headers, cookies,schema...]
 */

public class BasicMethods {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in/api";
	}
	 
	
	//@Test
	public void getMethod() {
		//We can also use when() method without given() method
		when()
			.get("/users/1")
		.then()
			.statusCode(200)
			.log().all();
		
		
	}
	
	@Test
	public void postMethod() {
		//have to use given() method in post since we have to pass the request body to create new record
		HashMap<String , String> loginReqBody=new HashMap<>();
		loginReqBody.put("username", "user3");
		loginReqBody.put("email", "abcde@gmail.com");
		loginReqBody.put("password", "pass123");
		
		given()
			.contentType("application/json")
			.body(loginReqBody)
		.when()
			.post("/login")
		.then()
			.statusCode(401)
			.log().all();
	}
	
	//@Test
	public void putMethod() {
		//same as post method have to use given() method in order update the details of the record as well as the path parameter
		
		when()
			.put("/users/1")
		.then()
			.statusCode(401);
		
	}
	
	//@Test
	public void deleteMethod() {
		// same as get method sometimes we can use given to pass the path parameter ( to delete a particular record)
		
		when()
			.delete("/users/2")
		.then()
			.statusCode(401);
		
	}

}
