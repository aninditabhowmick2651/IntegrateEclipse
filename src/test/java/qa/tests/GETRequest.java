package qa.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GETRequest {
	@Test
	public void callGET()
	{
		Response rest=RestAssured.get("https://reqres.in/api/users/2");
		System.out.println(rest.asString());
	}

}
