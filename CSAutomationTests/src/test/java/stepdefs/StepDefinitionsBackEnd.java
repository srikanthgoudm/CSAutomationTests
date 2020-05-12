package stepdefs;


import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.test.commons.PropertyFileReader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class StepDefinitionsBackEnd {
	RequestSpecification request;
	Response response;
	PropertyFileReader prop = new PropertyFileReader();
	Properties properties = prop.propertyFileReader();

	int ID;
	
	//Expected Results:
	String logo_ex = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png";
	String technical_doc_ex = "https://github.com/ethereum/wiki/wiki/White-Paper";
	String symbol_ex = "ETH";
	String date_added_ex = "2015-08-07T00:00:00.000Z";
	String platform_ex = "null";
	String tags_ex = "mineable";


	@Given("I want to retrive the ID of \"([^\"]*)\" using map Api")
	public void i_want_to_retrive_the_ID_of_using_map_Api(String cryptoCurrency) throws Throwable {
		RestAssured.baseURI= properties.getProperty("Endpoint");
		request = RestAssured.given();
		request.headers("X-CMC_PRO_API_KEY","df19249d-d59e-4d91-8af4-6ff15f7fcf35");
		response =request.get("v1/cryptocurrency/map");
		assertEquals(200, response.getStatusCode());
		
		JSONObject jsonReponseObject = new JSONObject(response.asString());
		ReadContext readJson =JsonPath.parse(jsonReponseObject.toString());
		System.out.println(cryptoCurrency.trim());
		int dataLength = readJson.read("$.data.length()");
		System.out.println(dataLength);
		for(int i = 0; i < dataLength; i++) {
			
			if(readJson.read("$.data["+i+"].symbol").equals(cryptoCurrency.trim())){
				ID = readJson.read("$.data["+i+"].id");
			}
		}

		System.out.println("ID for "+cryptoCurrency+" is "+ID);
		

	}

	@When("^I have the IDs I can request the Tools API to covert them to Bolivian Boliviano$")
	public void i_have_the_IDs_I_can_request_the_Tools_API_to_covert_them_to_Bolivian_Boliviano() throws Throwable {
		request.param("amount", "1");
		request.param("id",ID);
		request.param("convert","BOB");
		response =request.get("v1/tools/price-conversion");
		assertEquals(200, response.getStatusCode());
		
	}


	@Then("^I want to print the Boliviano Value$")
	public void i_want_to_print_the_Boliviano_Value() throws Throwable {
		JSONObject jsonReponseObject = new JSONObject(response.asString());
		ReadContext readJson =JsonPath.parse(jsonReponseObject.toString());
		double price = readJson.read("$.data.quote.BOB.price");
		System.out.println("The Bolivian Boliviano is "+price);
		
	}
	
	@When("I have the IDs I can request the info API to retrive the currency details")
	public void i_have_the_IDs_I_can_request_the_info_API_to_retrive_the_currency_details() {
		request.param("id",ID);
		response =request.get("v1/cryptocurrency/info");
		assertEquals(200, response.getStatusCode());
		
	
	}

	@Then("I want to validate the CryptoCurrency Details")
	public void i_want_to_validate_the_CryptoCurrency_Details() {
		JSONObject jsonReponseObject = new JSONObject(response.asString());
		ReadContext readJson =JsonPath.parse(jsonReponseObject.toString());
		String logo = readJson.read("$.data."+ID+".logo");
		System.out.println(logo);
		assertEquals(logo, logo_ex);
		
		String technical_doc = readJson.read("$.data."+ID+".urls.technical_doc[0]");
		System.out.println(technical_doc);
		assertEquals(technical_doc, technical_doc_ex);
		String symbol = readJson.read("$.data."+ID+".symbol");
		System.out.println(symbol);
		assertEquals(symbol, symbol_ex);
		String dateAdded = readJson.read("$.data."+ID+".date_added");
		System.out.println(dateAdded);
		assertEquals(dateAdded, date_added_ex);
		String tags = readJson.read("$.data."+ID+".tags[0]");
		System.out.println(tags);
		assertEquals(tags, tags_ex);
		
	}
	
	@Given("I want to get the details of CryptoCurrency with \"([^\"]*)\"")
	public void i_want_to_get_the_details_of_CryptoCurrency_with(int int1) {
		this.ID = int1;
		
		RestAssured.baseURI= properties.getProperty("Endpoint");
		request = RestAssured.given();
		request.param("id",int1);
		request.headers("X-CMC_PRO_API_KEY","df19249d-d59e-4d91-8af4-6ff15f7fcf35");
		
		
	}

	@When("I get the details from info API")
	public void i_get_the_details_from_info_API() {
		response =request.get("v1/cryptocurrency/info");
		assertEquals(200, response.getStatusCode());
		
	}

	@Then("I want to check whether CryptoCUrrency is minable or not and print the Cryptocurrency name")
	public void i_want_to_check_whether_CryptoCUrrency_is_minable_or_not_and_print_the_Cryptocurrency_name() {
		JSONObject jsonReponseObject = new JSONObject(response.asString());
		ReadContext readJson =JsonPath.parse(jsonReponseObject.toString());
		String tags = readJson.read("$.data."+ID+".tags[0]");
		String name = readJson.read("$.data."+ID+".name");
		System.out.println(name+" has tag "+tags);
		
	}



}