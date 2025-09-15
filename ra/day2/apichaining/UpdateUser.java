package ra.day2.apichaining;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUser {
	
	@Test
	public void updateUserbyID(ITestContext context) {
		
		String token="2e48843dc61bc849e81106c7d55ee0543c83cb296d93d5fee2bd9e3811137014";
		
		JSONObject data=new JSONObject();
		Faker fake =new Faker();
		
		data.put("name", fake.name().firstName());
		data.put("email", fake.internet().emailAddress());
		data.put("gender", "Male");
		data.put("status", "inactive");
		
		//int id_val=7440399;
		
		int id=(Integer) context.getAttribute("id");
		
		Response response=given()
		.headers("Authorization","Bearer "+token)
		.pathParam("id", id)
		.contentType(ContentType.JSON)
		.body(data.toString())
		.when()
		.put("https://gorest.co.in/public/v2/users/{id}");
		
		response.then()
		.statusCode(200).log().all();
	}
		
}
