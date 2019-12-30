package stepDefinitions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.core.IsNot;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

public class POSTProfileSteps {

   private static ResponseOptions<Response> response; 
	
	@Given("^I Perform POST operation for \"([^\"]*)\" with body$")
	public void i_Perform_POST_operation_for_with_body(String url, DataTable table){
	    
		List<List<String>> data = table.raw();
		
		//set body
		HashMap<String,String> body = new HashMap<>();
		body.put("name", data.get(1).get(0));  //Get first row first value
		
		//path params
		HashMap<String,String> pathParams = new HashMap<>();
		pathParams.put("profileNo", data.get(1).get(1)); //First row second column
	    
		response = RestAssuredExtension.PostOpsWithBodyAndPathParams(url, pathParams, body);	
	}
	
	@Then("^I should see the name as \"([^\"]*)\"$")
	public void i_should_see_the_name_as(String name){
		 assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
	}

	//refer DELETEPost.feature
	@Given("^I ensure to Perform POST operation for \"([^\"]*)\" with body as$")
	public void i_ensure_to_Perform_POST_operation_for_with_body_as(String url, DataTable table){
	   
		List<List<String>> data = table.raw();

	        Map<String, String> body = new HashMap<>();
	        body.put("id", data.get(1).get(0));
	        body.put("title", data.get(1).get(1));
	        body.put("author", data.get(1).get(2));

	        //Perform post operation
	        RestAssuredExtension.PostOpsWithBody(url, body);
		
	}

	@Given("^I Perform DELETE operation for \"([^\"]*)\"$")
	public void i_Perform_DELETE_operation_for(String url, DataTable table){
	   
		List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        //Perform Delete operation
        RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
		
	}

	@Given("^I perform GET operation with path parameter for \"([^\"]*)\"$")
	public void i_perform_GET_operation_with_path_parameter_for(String url, DataTable table){
		List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.GetWithPathParams(url, pathParams);
	}
	
	@Then("^I \"([^\"]*)\" see the body with title as \"([^\"]*)\"$")
	public void i_see_the_body_with_title_as(String condition, String title){
		if (condition.equalsIgnoreCase("should not"))
            assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
        else
            assertThat(response.getBody().jsonPath().get("title"), is(title));
  }
	
	@Given("^I Perform PUT operation for \"([^\"]*)\"$")
	public void i_Perform_PUT_operation_for(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

        Map<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        //Perform PUT operation
        RestAssuredExtension.PUTOpsWithBodyAndPathParams(url, body, pathParams);
	}


}
