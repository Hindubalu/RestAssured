package ra.day2;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

/* JSON Data Validation - verifying/(or validating) the data of the response from json format.
 * Approach 1 - without capturing the response we can directly validate the value in the then() part
 * Approach 2 - we can capture the response by stopping it in when() , after we can do various validation in the captured response
 * 
 * jsonDataValidation()--> validating the single data without capturing the response in json response body
 * jsonDajsonDataValidation()--> validating the single data after capturing the response in json response body
 * jsonDataValidationOfMultipleFields()--> validating multiple data from the entire json response body by creating json object
 * 
 * response.toString()-->Does NOT return the actual HTTP response body.,It returns a Java object’s string representation, typically something like:ClassName@hashcode
 *                      -This is useful for debugging purposes but NOT useful for processing the API’s JSON response.
 *                      
 * response.getBody().asString()-->Returns the actual response body content as a String.
 *                                -This is exactly what you should use when you want to parse or work with the API response payload.
 * 
 */


public class JSONDataValidationParsing {
	
	//@Test
	public void jsonDataValidation() {
		
		//Use json path finder to find the path of the field in json response in which you want to validate its data/information
		//Here we are validating the author of the first book is "Richard E. Silverman"
		
		given()
			.header("Authorization","Bearer qwerty987654321")
		.when()
			.get("https://demoqa.com/BookStore/v1/Books")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("books[0].author", equalTo("Richard E. Silverman"))
			.log().all();
		
	}
	
	//@Test
	public void jsonDataValidationCapturedResponse() {
		
		//If we capture the response and store it in a variable we can do more number of validations
		//Here we are validating the author of the first book is "Richard E. Silverman" after capturing the response
		
		Response response=given()
							.header("Authorization","Bearer qwerty987654321")
						  .when()
						  	.get("https://demoqa.com/BookStore/v1/Books");
		
		Assert.assertEquals(response.getStatusCode(),200);   //validating status code
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");  //validating the header
		
		String author_book1=response.jsonPath().get("books[0].author").toString();   // .toString() method is used, because .get() method's return type is object
		Assert.assertEquals(author_book1, "Richard E. Silverman");  //validating the value of author in book1
		                                                                                                
	}
	
	@Test
	public void jsonDataValidationOfMultipleFields() {
		
		//Let's say if we want to print author of all the books in the json respone. In order to do that first we have to create a json object.
		//Then traverse through the all objects in json array [here it is books]
		
		Response response=given()
							.header("Authorization","Bearer qwerty987654321")
							.contentType(ContentType.JSON)
						  .when()
						  	.get("https://demoqa.com/BookStore/v1/Books");
		
		
		//JSONObject jo=new JSONObject(response.toString());   //converting response to json object type
		JSONObject jo=new JSONObject(response.getBody().asString());    //converting response to json object type
		
		//Printing all the author in the book list
		for(int i=0;i<jo.getJSONArray("books").length();i++) {
			String authors=jo.getJSONArray("books").getJSONObject(i).get("author").toString();
			System.out.println(authors);				
		}
		
		// want to check whether the particular value is present or not irrespective of the order in which it presents.
		// Here the book name 'Learning JavaScript Design Patterns' is present or not
		
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("books").length();i++) {
			String book_title=jo.getJSONArray("books").getJSONObject(i).get("title").toString();			
			if (book_title.equals("Learning JavaScript Design Patterns")) {
				status=true;
				break;				
			}			
		}
		Assert.assertEquals(status, true);   // we are adding assert because the if condition doesn't make the test as pass or fail, it just skip the loop if the value we given is present.
	}
	
	

}
