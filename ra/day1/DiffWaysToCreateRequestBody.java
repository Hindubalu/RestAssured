package ra.day1;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*
 * 1. Using HashMap
 * 2. Using org.json 
 * 3. Using POJO class
 * 4. Using External json files
 */


public class DiffWaysToCreateRequestBody {
	
	//@Test
	public void usingHashMap() {
		
		//We can directly the pass the data (object of the hash map) to the body
		
		HashMap<Object, Object> data=new HashMap<>();
		data.put("name", "student1");
		data.put("email", "sample1@gmail.com");
		data.put("mobile", "9876543234");
		
		String coursesArr[]= {"Java","ra","Testng"};
		
		data.put("courses",coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("/login")
		.then()
			.statusCode(401)
			.log().all();		
	}
	
	//@Test
	public void usingOrgJson() {
		
		//If we are using a org.json format the content type should be json format but the body should be in string format
		//in order to do that we have to use .toString method in the request body.
		
		JSONObject data=new JSONObject();
		data.put("name", "student2");
		data.put("email", "sample2@gmail.com");
		data.put("mobile", "9876543235");
		
		String coursesArr[]= {"Java","ra","Testng"};
		
		data.put("courses",coursesArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("/login")
		.then()
			.statusCode(401)
		.log().all();		
		
	}
	

	//@Test
	public void usingPOJOClass() {
		
		//POJO-Plain old java object class - have to create setter and getter method for the all the variables we gonna use in post request body
		//And create a object to use those methods , and can directly pass the object in the .body() method
		
		PostRequestBodyPOJO data= new PostRequestBodyPOJO();
		data.setName("student3");
		data.setEmail("sample3@gmail.com");
		data.setMobile("987654347");
		
		String coursesArr[]= {"Java","ra","Testng"};
		data.setCoursesArr(coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("/login")
		.then()
			.statusCode(401)
			.log().all();
		
	}
	
	@Test
	public void usingExternalJsonFile() throws FileNotFoundException {
		
		//File to open the file
		//FileReader to read the file which is already open (f-object of the file)
		//JsonTokener is to create token and have to pass the object of File Reader
		//JsonObject is to create a json as we used in the 2nd way 
		//And we have to use .toString() method to pass the data in the body
		
		File f=new File(".\\requestbody.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("/login")
		.then()
			.statusCode(401)
			.log().all();
		
		
		
		
	}

}
