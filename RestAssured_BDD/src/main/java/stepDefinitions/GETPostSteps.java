package stepDefinitions;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.Posts;
import pojo.PostsWithBuliderPattern;
import utilities.APIConstant;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtensionv2;

import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GETPostSteps {

   private static ResponseOptions<Response> response; 

	@Given("^I perform GET operation for \"([^\"]*)\"$")
	public void i_perform_GET_operation_for(String url) {
		response=RestAssuredExtension.GetOps(url);
	}

	@Then("^I should see the author name as \"([^\"]*)\"$")
	public void i_should_see_the_author_name_as(String author){
	   assertThat(response.getBody().jsonPath().get("author"), hasItem("Ganesh P"));
		
	}

	@Then("^I should see the title as \"([^\"]*)\"$")
	public void i_should_see_the_title_as(String arg1) {
	
	}

	@Then("^I should see the author names$")
	public void i_should_see_the_author_names() {
		BDDStyledMethod.PerformContainsCollection();
	}
	
	@Then("^I should see verify GET Parameter$")
	public void i_should_see_verify_GET_Parameter() {
		BDDStyledMethod.PerformPathParameter();
	}
	
	@Then("^I should see verify GET Parameter with Query Param$")
	public void i_should_see_verify_GET_Parameter_with_Query_Param(){
		BDDStyledMethod.PerformQueryParameter();
	}
	
	@Given("^I perform POST operation for \"([^\"]*)\"$")
	public void i_perform_POST_operation_for(String arg1){
		BDDStyledMethod.PerformPOSTWithBodyParameter();
	}
	
	@Given("^I perform GET operation for the pojo test \"([^\"]*)\"$")
	public void i_perform_GET_operation_for_the_pojo_test(String url) throws Throwable {
		response=RestAssuredExtension.GetOps(url);
	}
	
	@Then("^I should see the author name as for the pojo test as \"([^\"]*)\"$")
	public void i_should_see_the_author_name_as_for_the_pojo_test_as(String authorName){
		Posts post = response.getBody().as(Posts.class);// using pojo class 
		assertThat(post.getAuthor(), equalTo(authorName));
	}
	
	
	@Given("^I perform GET operation with query parameters for address \"([^\"]*)\"$")
	public void i_perform_GET_operation_with_query_parameters_for_address(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("id",data.get(1).get(0));
		response = RestAssuredExtension.GetWithQueryParams(url, queryParams);
		
	}

	@Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
	public void i_should_see_the_street_name_as_for_the_address(String streetName, String type) throws Throwable {
	    Location[] location = response.getBody().as(Location[].class);  // address is containg a array of values
	    Address address = location[0].getAddress().stream().filter(x->x.getType().equalsIgnoreCase(type)).findFirst().orElse(null);
	    assertThat(address.getStreet(), equalTo(streetName));
	}
	
	
	@Then("^I should see the author name as \"([^\"]*)\" with json schema validation$")
	public void i_should_see_the_author_name_as_with_json_schema_validation(String name) throws Throwable {
	   String responseBody = response.getBody().asString();
	   assertThat(responseBody, matchesJsonSchemaInClasspath("posts.json"));
	}

	@Given("^I perform GET operation with query parameters for address version(\\d+) RestAssuredExtension \"([^\"]*)\"$")
	public void i_perform_GET_operation_with_query_parameters_for_address_version_RestAssuredExtension(int ur, String uri, DataTable table){
		
		List<List<String>> data = table.raw();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("id",data.get(1).get(0));
		RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri,APIConstant.ApiMethods.GET,null);
		response =restAssuredExtensionv2.ExecuteWithQueryParams(queryParams);
	}
	
	//Using builder pattern test
	@Given("^I perform GET operation for the pojo test \"([^\"]*)\" for builder pattern$")
	public void i_perform_GET_operation_for_the_pojo_test_for_builder_pattern(String uri, DataTable table){
		List<List<String>> data = table.raw();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("id",data.get(1).get(0));
		RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri,APIConstant.ApiMethods.GET,null);
		response = restAssuredExtensionv2.ExecuteWithQueryParams(queryParams);
	}

	@Then("^I should see the author name as for the Builder Pattern test as \"([^\"]*)\"$")
	public void i_should_see_the_author_name_as_for_the_Builder_Pattern_test_as(String authorName){
//		 Posts posts= new Posts.Builder().build();
//		 Posts resp=response.getBody().as(posts.getClass());
//		 assertThat(resp.getAuthor(), equalTo(authorName));
		
	}
	
	
	
//	 @And("^I perform GET operation with path parameter for address \"([^\"]*)\"$")
//	    public void iPerformGETOperationWithPathParameterForAddress(String uri, DataTable table) throws Throwable {
//
//	        var data = table.raw();
//
//	        Map<String, String> queryParams = new HashMap<>();
//	        queryParams.put("id", data.get(1).get(0));
//
//	        //response
//	        //response = RestAssuredExtension.GetWithQueryParamsWithToken(url, queryParams, response.getBody().jsonPath().get("access_token"));
//
//	        RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri,"GET", token );
//	        response = restAssuredExtensionv2.ExecuteWithQueryParams(queryParams);
//	    }
//	
//  HashMap<String, String> body = new HashMap<>();
//  body.put("email", data.get(1).get(0));
//  body.put("password", data.get(1).get(1));
//	 RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri, APIConstant.ApiMethods.POST,null);
//   token = restAssuredExtensionv2.Authenticate(body);
// or   Serializing POJO classes to JSON object in RestAssured
// LoginBody loginbody = new LoginBody()	{
// loginBody.setEmail(data.get(1).get(0));
// loginBody.setPassword(data.get(1).get(1);
//}
	
	
}
