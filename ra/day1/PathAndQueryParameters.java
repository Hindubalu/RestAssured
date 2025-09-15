package ra.day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

/*
 * Path Parameters- is used for routing (to find the path of the specific resource)
 * Query Parameters - is used for filtering (to get all resource based on a particular field)
 */

public class PathAndQueryParameters {
	
	@Test
	public void PathAndQueryParams() {
		
		//we have to pass the pass parameter variable in the URL where as query parameter will automatically go with the requests
		//We can't create multiple values for path parameter in single pathParam() method same goes for query parameter.
		
		given()
			.pathParam("pathParameter", "value")
			.queryParam("queryParam1", "qParam1_value")
			.queryParam("queryParam2","qParam2_value")
		.when()
			.get("/{pathParameter}")
		.then()
			.log().all();
	}

}
