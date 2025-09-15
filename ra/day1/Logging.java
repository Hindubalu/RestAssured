package ra.day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

/*
 * Logging is nothing but printing the information in the console
 */
public class Logging {
	
	@Test
	public void loggingInConsole() {
		
		given()
		.when()
			.get("https://reqres.in/api/users/1")
		.then()
			.log().all()        //print everything
			.log().body()       //print response body alone
			.log().cookies()    //print cookies alone
			.log().headers();   //print headers alone
		
	}

}
