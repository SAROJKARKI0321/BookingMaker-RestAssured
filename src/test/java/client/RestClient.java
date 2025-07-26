package client;

import config.TestConfig;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.qameta.allure.restassured.AllureRestAssured;
import utility.TokenManager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestClient {


    private RequestSpecification reqSpec;
    private ResponseSpecification resSpec;


    public  RestClient(){
        setUpRequestSpec();
        setUpResponseSpec();
    }
    public RequestSpecification getReqSpec() {
        return reqSpec;
    }

    public ResponseSpecification getResSpec() {
        return resSpec;
    }

    private  void setUpRequestSpec(){
        RequestSpecBuilder builder=new RequestSpecBuilder()
                                      .setBaseUri(TestConfig.BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured());

        if(Boolean.parseBoolean(TestConfig.LOG_REQUESTS)){
            builder.addFilter(new RequestLoggingFilter());
        }
        if(Boolean.parseBoolean(TestConfig.LOG_RESPONSES)){
            builder.addFilter(new ResponseLoggingFilter());
        }
        reqSpec=builder.build();


    }
    private void setUpResponseSpec(){
        int responseTimeouts=Integer.parseInt(TestConfig.RESPONSE_TIMEOUT);
        resSpec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectResponseTime(lessThan((long) responseTimeouts), TimeUnit.SECONDS)
                .build();
    }
    @Step("Execute GET request to:{endpoint}")
    public Response get(String endpoint){
        return  given().spec(reqSpec)
                .when()
                .get(endpoint);
    }
    @Step("Execute GET request to :{endpoint} with params")
    public  Response get(String endpoint, Map<String,Object> queryParams){
        return given().spec(reqSpec)
                .queryParams(queryParams)
                .when()
                .get(endpoint);

    }
    @Step("Execute POST request to: {endpoint}")
    public Response post(String endpoint, Object body) {
        return given()
                .spec(reqSpec)
                .body(body)
                .when()
                .post(endpoint);
    }
    @Step("Execute PUT request to: {endpoint}")
    public Response put(String endpoint, Object body){
        return  given().spec(authReqSpec())
                .body(body)
                .when()
                .put(endpoint);
    }
    @Step("Execute DELETE request to: {endpoint}")
    public Response delete(String endpoint){
        return  given().spec(authReqSpec())
                .when()
                .delete(endpoint);

    }


    private  RequestSpecification authReqSpec(){
        return  new RequestSpecBuilder().addRequestSpecification(reqSpec)
                .addCookie("token", TokenManager.getToken())
                .build();
    }





}
