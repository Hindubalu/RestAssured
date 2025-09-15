package ra.day2;

import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;

/*
 * Json Schema Validation - validating the type of the data
 * In order to do that, use any online platform to convert json response body to json schema and save the .json file in the resource folder.
 * JSON Response (.json) format --> JSON Schema (.json) format
 */

public class JSONSchemaValidation {
	
	@Test
	public void jsonSchemaValidation() {
		given()
		.when()
		.get("https://demoqa.com/BookStore/v1/Books")
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
		
		
	}

}
