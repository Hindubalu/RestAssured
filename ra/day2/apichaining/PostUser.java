package ra.day2.apichaining;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class PostUser {
	
	@Test
	public void createUser(ITestContext context) {
		
		String token="2e48843dc61bc849e81106c7d55ee0543c83cb296d93d5fee2bd9e3811137014";
		
		JSONObject data=new JSONObject();
		Faker fake =new Faker();
		
		data.put("name", fake.name().firstName());
		data.put("email", fake.internet().emailAddress());
		data.put("gender", "Female");
		data.put("status", "inactive");
		
		int id=given()
		.headers("Authorization","Bearer "+token)
		.contentType(ContentType.JSON)
		.body(data.toString())
		.when()
		.post("https://gorest.co.in/public/v2/users")
		.jsonPath().get("id");
		
		System.out.println(id);
		
		context.setAttribute("id",id);
		
		
		
	
		
	}
	

}
