package ra.day1;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Map;

/*
 * Cookies - Cookies are small pieces of data sent by the server to the client, and they are stored on the client side. (session-id)
 * Headers - Headers are metadata sent along with the API request or response. (Content type, authorization, accept)
 */

public class CookiesAndHeaders {

	//@Test
	public void withoutCapturingResponse_Cookie() {
		
		//If we want to validate only one cookie value we can use this method --> .cookie()
		
		given()
		.when()
			.get("")
		.then()
			.cookie("cookieName", "cookieValue")
			.log().all();
		
	}
	
	//@Test
	public void capturingResponse_Cookies() {
		
		//We can also validate the entire cookie information by stopping it in the when() method and use response variable to store the response then validate it.
		//To validate single cookie use .getCookie() method directly in response without then() method - return type:String
		//To validate multiple cookie use .getCookies() method directly in response without then() method - return type:Map
		
		Response response=given()
						 .when()
						 	.get("");
		// For single Cookie
		String cookie_value=response.getCookie("cookieName");
		System.out.println("Captured cookie value: "+cookie_value);
		
		//For all(/multiple) cookie
		Map<String, String> allCookies=response.getCookies();
		System.out.println(allCookies.keySet());  //Give all the key set of the cookies [Will return in array]
		
		for(String key:allCookies.keySet()) {
			String cookie_key_value=response.getCookie(key);  // get the particular cookie value
			System.out.println(key+" "+cookie_key_value);
			
		}
		
	}
	
	@Test
	public void withoutCapturingResponse_Header() {
		
		//If we want to validate only one header value we can use this method --> .header() 
		
		given()
		.when()
			.get("")
		.then()
			.header("headerName", "headerValue");
	}
	
	@Test
	public void capturingResponse_Headers() {
		
		//We can also validate the entire cookie information by stopping it in the when() method and use response variable to store the response then validate it.
		//To validate single header use .getHeader() method directly in response without then() method - return type:String
		//To validate multiple header use .getHeaders() method directly in response without then() method - return type:Header
		
		Response responseHeader=given()
				.when()
					.get("");
		
		String header_value=responseHeader.getHeader("HeaderName");  // Header Format:- HeaderName: HeaderValue
		System.out.println("Header value: "+header_value);
		
		Headers header_key=responseHeader.getHeaders(); //Headers Format:-  HeaderName1: Header1Value
		                                                //                  HeaderName2: Header2Value
		
		for(Header headerKey:header_key) {
			System.out.println(headerKey.getName()+" : "+headerKey.getValue());  //.getName() - give header name ; .getValue() - give header value
			
		}
	}
}
