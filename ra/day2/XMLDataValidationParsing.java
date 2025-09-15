package ra.day2;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

/*
 * XML Data Validation - verifying/(or validating) the data of the response from xml format.
 * Approach 1 - without capturing the response we can directly validate the value in the then() part
 * Approach 2 - we can capture the response by stopping it in when() , after we can do various validation in the captured response
 * 
 * xmlDataValidation()--> validating the single data without capturing the response in xml response body
 * xmlDataValidationCapturedResponse()--> validating the single data after capturing the response in xml response body
 * xmlDataValidationMultipleFields()--> validating multiple data from the entire xml response body
 * 
 * response.toString()--> returns a string representation of the Response object, not the actual XML content. 
 *                       -typically something like:ClassName@hashcode
 *                       -never use this method when you need the actual response body content
 *                       
 * response.getBody().asString()--> returns the response body content as a string, which contains the XML payload you want to parse.
 *                                 -Correct for parsing or validating response payload.
 * 
 */
public class XMLDataValidationParsing {
	
	//@Test
	public void xmlDataValidation() {
		
		//In xml; if we want to find the path for particular field it is same as absolute xpath traverse through every node from root but the only difference is here we have to use . instead of slash
		//Here we are validating the name of the first traveller is "Vijay"
		
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml")
			.body("TravelInformationResponse.page", equalTo(1))
			.body("TravelInformationResponse.travelers.TravelInformation[1].name",equalTo("Vijay"));		
	}

	//@Test
	public void xmlDataValidationCapturedResponse() {
		
		//If we capture the response and store it in a variable we can do more number of validations
		//Here we are validating the name of the first traveller is "Vijay" after capturing the response
		
		Response response=given()
						  .when()
						  	 .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(response.getStatusCode(), 200); //validating the status code
		Assert.assertEquals(response.getHeader("Content-Type"), "application/xml");  //validating the header
		
		String pageNumber=response.xmlPath().get("TravelInformationResponse.page").toString();   // .toString() method is used, because .get() method's return type is object
		Assert.assertEquals(pageNumber, 1);   //validating the page number
		
		String traveler1_name=response.xmlPath().get("TravelInformationResponse.travelers.TravelInformation[1].name").toString();  // .toString() method is used, because .get() method's return type is object
		Assert.assertEquals(traveler1_name, "Vijay");	//validating the name of the first travelers.
	}
	
	@Test
	public void xmlDataValidationMultipleFields() {
		
		//Let's say if we want to print name of all the traveler in the xml respone. In order to do that first we have to create a xmlPath object.
		//Then traverse through the all the node in xml [here it is TravelInformation]
		
		Response response=given()
							.contentType(ContentType.XML)
						.when()
							.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		//XmlPath xo=new XmlPath(response.toString());
		XmlPath xo=new XmlPath(response.getBody().asString());  // converting the response to xmlpath object
		
		//Validating the size of travellers.
		List<String> travellers=xo.getList("TravelInformationResponse.travelers.TravelInformation");
		Assert.assertEquals(travellers.size(), 10);
		
		// want to check whether the particular value is present or not irrespective of the order in which it presents.
		//Here verifying whether the name 'Vijay' is present or not
		
		List<String> travellers_name=xo.getList("TravelInformationResponse.travelers.TravelInformation.name");
		boolean status=false;
		for(String travellerName: travellers_name) {
			if(travellerName.equals("Vijay")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);		 // we are adding assert because the if condition doesn't make the test as pass or fail, it just skip the loop if the value we given is present.
	}
	
}
