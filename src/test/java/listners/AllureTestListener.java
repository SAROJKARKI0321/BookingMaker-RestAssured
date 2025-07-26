package listners;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Stage;
import io.qameta.allure.model.TestResult;
import org.testng.ITest;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureTestListener implements ITestListener, TestLifecycleListener {

    @Override
    public  void onTestStart(ITestResult result){
        System.out.println("Starting test:"+result.getMethod().getMethodName());

    }
    @Override
    public  void onTestSuccess(ITestResult result){
        System.out.println("Test passed:"+ result.getMethod().getMethodName());
        attachExecutionInfo("Test passsed succesfully");

    }
    @Override
    public  void onTestFailure(ITestResult result){
        System.out.println("Test failed:"+result.getMethod().getMethodName());
        attachExecutionInfo("Test failed"+ result.getThrowable().getMessage());

    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠ Test skipped: " + result.getMethod().getMethodName());
        attachExecutionInfo("Test was skipped");
    }



    @Attachment(value="Execution info",type = "text/plain")
    private  String attachExecutionInfo(String message){
        return "Timestamp:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+
                "\nMesssage:"+ message;
    }
    private String attachStackTrace(Throwable throwable){
        StringBuilder sb=new StringBuilder();
        sb.append(throwable.toString()).append("\n");
        for(StackTraceElement element: throwable.getStackTrace()){
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
    @Override
    public  void beforeTestStop(TestResult result){
        if(result.getStage()== Stage.RUNNING){
            result.setStage(Stage.FINISHED);
        }
    }
}
