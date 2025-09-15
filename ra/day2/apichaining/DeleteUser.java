package ra.day2.apichaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	//41c4202e90c0899822a373afa64967f6cdf36b2b9767bfa7b6c1f381cf437279
	
	
	@Test	
	public void getUser(ITestContext context) {
		String token="2e48843dc61bc849e81106c7d55ee0543c83cb296d93d5fee2bd9e3811137014";
		
		//int id_val=7440399;
		int id=(Integer) context.getAttribute("id");
		
		given()
		.headers("Authorization","Bearer "+token)
		.pathParam("id", id)
		.when()
		.delete("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		.statusCode(204)
		.log().all();
	}

}
