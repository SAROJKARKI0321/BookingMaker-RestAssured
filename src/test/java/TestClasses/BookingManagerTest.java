package TestClasses;

import POJO.Booking;
import assertions.BookingAssertions;
import client.ApiEndpoints;
import com.github.javafaker.Faker;
import factory.BookingFactory;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Epic("Booking Management")
@Feature("CRUD Operations")
public class BookingManagerTest extends BaseTest {
    private static int bookingId;
    private static Faker faker = new Faker();
    private Booking booking;

    @Test(priority = 1, description = "Create a new Booking")
    @Story("Create Booking")
    @Description("Test to create new booking with valid data")
    public void testCreateBooking() {
        booking = BookingFactory.generateRandomBooking();

        Response response = restClient.post(ApiEndpoints.BOOKING, booking);
        BookingAssertions assertions = new BookingAssertions(response);
        assertions.hasStatusCode(200)
                .isNotEmpty()
                .hasValidBookingId()
                .hasRequiredFields();


        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking created with ID:" + bookingId);


    }

    @Test(priority = 2, dependsOnMethods = "testCreateBooking")
    @Story("Retrieve booking")
    @Description("Test to retrieve an existing booking by ID AND verify the data matches")
    public void testRetriveBooking() {
        Response response = restClient.get(ApiEndpoints.getBookingById(bookingId));
        BookingAssertions assertions = new BookingAssertions(response);
        assertions.hasStatusCode(200).matchesBookingData(booking)
                .isNotEmpty();


        System.out.println("Booking retived successfully with ID:" + bookingId);


    }

    @Test(priority = 3, dependsOnMethods = "testRetriveBooking")
    @Story("Update Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to update an existing booking and verify the changes")
    public void testUpdateBooking() {
        String updatedLastname = faker.name().lastName();
        booking.setLastname(updatedLastname);
        Response response = restClient.put(ApiEndpoints.getBookingById(bookingId), booking);
        BookingAssertions assertions = new BookingAssertions(response);
        assertions.matchesBookingData(booking)
                .hasFieldWithValue("lastname", updatedLastname);

        System.out.println("Booking updates successfully, last name changed to :" + updatedLastname);

    }

    @Test(priority = 4)
    @Story("Delete Booking")
    @Description("Test to delete the booking created")
    public void testDeleteBooking() {
        Response response = restClient.delete(ApiEndpoints.getBookingById(bookingId));
        BookingAssertions assertions = new BookingAssertions(response);
        assertions.hasStatusCode(201);


    }

    @Test(priority = 5, dependsOnMethods = "testDeleteBooking")
    @Story("Retrieve booking which was delete")
    @Description("Test to retrieve booking by ID AND verify it doesnt exists")
    public void testRetriveBookingAfterDlete() {
        Response response = restClient.get(ApiEndpoints.getBookingById(bookingId));
        BookingAssertions assertions = new BookingAssertions(response);
        assertions.hasStatusCode(404);


        System.out.println("No Booking found with the ID:" + bookingId);


    }
}
