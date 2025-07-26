package utility;

import config.TestConfig;
import io.qameta.allure.Allure;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private  int retryCount=0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!Boolean.parseBoolean(TestConfig.RETRY_ENABLED)){
            return false;
        }
        if(retryCount<Integer.parseInt(TestConfig.MAX_RETRY_COUNT)){
            retryCount++;

            String testName=iTestResult.getMethod().getMethodName();
            String className=iTestResult.getTestClass().getName();
            Allure.addAttachment("Retry Info","Test:"+testName,"failed,Retrying attemp:"+ (retryCount+1));
            return true;
        }
        return false;

    }
}
