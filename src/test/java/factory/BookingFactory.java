package factory;

import POJO.Booking;
import POJO.BookingDates;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class BookingFactory {
    private static final Faker faker=new Faker();
    private static Random random=new Random();

    public static Booking generateRandomBooking(){
        String fName=faker.name().firstName();
        String lName=faker.name().lastName();
        int price= random.nextInt(500)+100;
        boolean pricePaid=random.nextBoolean();

        LocalDate checkinDate= LocalDate.now().plusDays(random.nextInt(30));
        LocalDate checkoutDate=checkinDate.plusDays(random.nextInt(15));
        BookingDates dates=new BookingDates(checkinDate.toString(),checkoutDate.toString());
        String additionalneeds=faker.options().option("Dinner","Spa","Swimming","Airpot Pickup","Airpot Drop");

        return  new Booking(fName,lName,price,pricePaid,dates,additionalneeds);


    }


}
