package assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import static org.hamcrest.Matchers.*;

public class ResponseAssertions<T extends  ResponseAssertions<T>> {
    private Response response;
    private SoftAssert softAssert;

    public ResponseAssertions(Response response){
        this.response=response;
        softAssert=new SoftAssert();
    }
    @SuppressWarnings("unchecked")
    protected  T self(){
        return  (T) this;
    }

    @Step("Verify the statusCode is {expectedStatusCode}")

    public  T hasStatusCode( int expectedStatusCode){
        int actualstatusCode=response.getStatusCode();
        Assert.assertEquals(actualstatusCode,expectedStatusCode,"Wrong statuscode");
        return self();
    }
    @Step("Verify response time is less than {maxResponseTimeMs} ms")
    public  T hasResponseTimeLessThan(long maxResponseTimeMs){
        long acutalResponseTime=response.getTime();
        Assert.assertTrue(acutalResponseTime<maxResponseTimeMs,"Response took longer time than expected");
        return self();

    }
    @Step("Verify response body contains field {fieldPath}")
    public T hasFieldInBody(String fieldPath){
        response.then().body(fieldPath,notNullValue());
        return self();
    }
    @Step("Verify field {fieldPath} has value {expectedValue}")
    public  T hasFieldWithValue(String fieldPath, Object expectedValue){
        response.then().body(fieldPath,equalTo(expectedValue));
        return  self();
    }
    @Step("Verify response is not empty")
    public T isNotEmpty() {
        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        return self();
    }

    public  Response getResponse(){
        return  response;

    }

    @Step("Validate all soft Assertions")
    public  void validateSoftAssertions(){
        softAssert.assertAll();
    }

}
