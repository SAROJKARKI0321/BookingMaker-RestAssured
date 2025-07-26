package TestClasses;

import client.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.TokenManager;
import static org.hamcrest.Matchers.*;

public class BaseTest {
    protected  String token;
    protected RestClient restClient;

    @BeforeClass(alwaysRun = true)
    @Step("Setup test Suite")
    public void setUp(){
        System.out.println("-----TEST SUITE SETUP-------");
        restClient=new RestClient();

        token=TokenManager.getToken();





    }
}
