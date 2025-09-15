package ra.day2.apichaining;
import static io.restassured.RestAssured.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

	@Test	
	public void getUser(ITestContext context) {
		
		String token="2e48843dc61bc849e81106c7d55ee0543c83cb296d93d5fee2bd9e3811137014";
		
		//int id_val=7440399;
		int id=(int) context.getAttribute("id");
		
		System.out.println("GET METHOD/n");
		given()
		.headers("Authorization","Bearer "+token)
		.pathParam("id", id)
		.when()
		.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	
	
}
