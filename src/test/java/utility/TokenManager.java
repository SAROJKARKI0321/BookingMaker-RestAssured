package utility;

import POJO.Login;
import client.ApiEndpoints;
import client.RestClient;
import config.TestConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {
    public static String token;
    private static RestClient restClient=new RestClient();
    @Step("Get authentication token")
    public static String getToken(){
        if(token==null){
            generateToken();
        }
        return token;
    }



    private static void generateToken(){
        Login loginpayload=new Login(TestConfig.USERNAME,TestConfig.PASSWORD);

        Response response=restClient.post(ApiEndpoints.AUTH,loginpayload);
        if(response.getStatusCode()==200) {
            token = response.jsonPath().getString("token");

            if (token == null || token.isEmpty()) {
                throw new RuntimeException("Token is empty /null");
            }
        }else {
            throw new RuntimeException("Authentication failed with status code:"+ response.getStatusCode());
        }


    }

}
