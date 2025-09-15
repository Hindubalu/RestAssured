package ra.day2;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;

/*
 * XML Schema Validation - validating the type of the data
 * In order to do that, use any online platform to convert xml response body to xml schema and save the .xsd file in the resource folder.
 * XML Response (.xml) format --> XML Schema (.xsd) format
 */

public class XMLSchemaValidation {
	
	@Test
	public void xmlSchemaValidaton() {
		
		given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlschema.xsd"));
		
		
		
	}

}
