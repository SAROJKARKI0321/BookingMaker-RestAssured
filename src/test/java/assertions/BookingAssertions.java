package assertions;

import POJO.Booking;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookingAssertions extends  ResponseAssertions<BookingAssertions>{
    private  Response response;

    public BookingAssertions(Response response){
        super(response);
    }

    @Step("Verify booking id is valid")

    public  BookingAssertions hasValidBookingId(){
        int bookingId=getResponse().jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId>0,"Booking Id should be a positive non zero integer");
        return  this;

    }
    @Step("Verify booking has required fields")
    public BookingAssertions hasRequiredFields() {
        return hasFieldInBody("booking.firstname").
        hasFieldInBody("booking.lastname").
        hasFieldInBody("booking.totalprice").
        hasFieldInBody("booking.depositpaid").
        hasFieldInBody("booking.bookingdates");


}
    @Step("Verify booking matches expected data")
    public BookingAssertions matchesBookingData(Booking expectedBooking) {
        return   hasFieldWithValue("firstname", expectedBooking.getFirstname())
                . hasFieldWithValue("lastname", expectedBooking.getLastname())
                .hasFieldWithValue("totalprice", expectedBooking.getTotalprice())
                . hasFieldWithValue("depositpaid", expectedBooking.isDepositpaid())
                . hasFieldWithValue("additionalneeds", expectedBooking.getAdditionalneeds());

    }
    }
