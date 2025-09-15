package ra.day1;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerGenerator {
	
	@Test
	public void fakerGeneratorDummy() {
		Faker fake=new Faker();
		String firstName=fake.name().firstName();
		String email=fake.internet().emailAddress();
		System.out.println("First name: "+firstName);
		System.out.println("Email: "+email);
		
	}

}
