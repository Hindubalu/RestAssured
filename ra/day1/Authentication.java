package ra.day1;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

/*
 * Basic Authentication: Like showing your ID card - you send your user name and password with every request, encoded in Base64.
 * Digest Authentication: More secure than Basic - the server sends a challenge, and you respond with a hashed version of your credentials.
 * PreEmptive Authentication: Sending your credentials immediately without waiting for the server to ask for them first.
 * OAuth 1.0: Like getting a temporary visitor pass - involves multiple steps to get a token, includes signatures for security.
 *            1. Consumer Key (Your App ID)-What it is: A public identifier for your application
 *            2. Consumer Secret (Your App Password): A secret password that proves your app is legitimate
 *            3. Access Token (User's Permission Slip): Proves a specific user gave your app permission
 *            4. Token Secret (User's Secret Key): A secret key tied to that specific user's access token
 * 
 * OAuth 2.0: Simpler than OAuth 1.0 - you get an access token after authorization, then use that token for requests.
 * Bearer Authentication: You carry a token (like a security badge) and present it with each request.
 * API Key: A unique identifier/password that identifies your application to the API.
 * 
 */

public class Authentication {
	
	@Test
	public void basicAuth() {
		
		given()
		.auth().basic("usernmae", "password")  //have to pass the username and password
		.when()
		.get("/endpoints")
		.then()
		.statusCode(200);
		
	}
	
	public void digestAuth() {
		given()
		.auth().digest("usernmae", "password")  //same as basic auth but the internal flow will be different
		.when()
		.get("/endpoints")
		.then()
		.statusCode(200);
		
	}
	
	public void preemptiveAuth() {
		
		given()
		.auth().preemptive().basic("usernmae", "password")   //sends credentials immediately
		.when()
		.get("/endpoints")
		.then()
		.statusCode(200);
		
	}
	
	public void oauth1() {
		
		given()
		.auth().oauth( "consumer-key","consumer-secret","access-token","token-secret") 
		.when()
		.get("/endpoints")
		.then()
		.statusCode(200);
	}
	
	public void oauth2() {
		
		given()
		.auth().oauth2("accessToken")
		.when()
		.get("/endpoints")
		.then()
		.statusCode(200);
		
	}
	
	public void bearerAuth() {
		
		//have to pass the value in the .header() method
		
		given()
		.header("Authorization", "Bearer token_value")
		.when()
		.get("/endpoints")
		.then()
		.statusCode(200);
		
	}
	
	public void apikeyAuth() {
		
		//Method1: in .queryParam() method
		
		given()
		.queryParam("ApiKey", "Value")    
		.when()
		.get("endpoints")
		.then()
		.statusCode(200);
		
		 // Method 2: As header
		
        given()
            .header("X-API-Key", "apiKey_value")
        .when()
            .get("/endpoints")
        .then()
            .statusCode(200);
        
        // Method 3: As custom header name
        given()
            .header("Authorization", "ApiKey apiKey_value")
        .when()
            .get("/endpoints")
        .then()
            .statusCode(200);

		
	}

}
