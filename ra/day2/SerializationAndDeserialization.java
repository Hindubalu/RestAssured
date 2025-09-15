package ra.day2;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Serialization:
 *   - POJO --> JSON
 *   - Serialization is the process of converting a Java object (or any object in programming) into a format that 
 *                                  can be transmitted over a network or saved, such as JSON or XML.
 *   - why needed? -> When you send data in an API request (like a POST request), you send data in a structured format (usually JSON or XML). 
 *                     Serialization converts your object into that format.
 * De_serialization: 
 *   - JSON --> POJO
 *   - De-serialization is the process of converting a response body (JSON/XML) into a Java object 
 *                               so that it becomes easy to manipulate and use in your test code.
 *   - Why needed? -> After sending a request, the API sends a JSON or XML response, and you may want to convert it into an object 
 *                     to easily read specific fields or perform validations.
 */
public class SerializationAndDeserialization {
	
	//@Test
	public void serialization() throws JsonProcessingException {
		
		UserPojo data=new UserPojo();
		data.setId(0);
		data.setName("User1");
		data.setEmail("example@gmail.com");
		data.setCourse("RestAssured");
		
		ObjectMapper objectMapper=new ObjectMapper();
		 
		String jsonData=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);  //converting pojo to json class.
		
		System.out.println(jsonData);
		
		
		
		
	}
	
	@Test
	public void deserialization() throws JsonProcessingException {
		
		String jsondata="{\r\n"
				+ "  \"id\" : 0,\r\n"
				+ "  \"name\" : \"User1\",\r\n"
				+ "  \"email\" : \"example@gmail.com\",\r\n"
				+ "  \"course\" : \"RestAssured\"\r\n"
				+ "}";
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		UserPojo up=objectMapper.readValue(jsondata, UserPojo.class);    //converting json to pojo class
		
		System.out.println("Id: "+up.getId());
		System.out.println("Name: "+up.getName());
		System.out.println("Email: "+up.getEmail());
		System.out.println("Course: "+up.getCourse());
		
	}

}
