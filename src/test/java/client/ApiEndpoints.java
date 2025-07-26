package client;

import config.TestConfig;

public class ApiEndpoints {
    public  static  final String AUTH= TestConfig.AUTH_ENDPOINT;
    public  static  final String BOOKING=TestConfig.BOOKING_ENDPOINT;

    public  static String getBookingById(int bookingId){
        return BOOKING+"/"+bookingId;
    }

}
