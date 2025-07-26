package config;

public class TestConfig {
    private  static  final ConfigManager config=    ConfigManager.getInstance();

    // API Configuration
    public static final String BASE_URI = config.getProperty("api.base.uri");
    public static final String AUTH_ENDPOINT = config.getProperty("api.auth.endpoint");
    public static final String BOOKING_ENDPOINT = config.getProperty("api.booking.endpoint");

    // Authentication
    public static final String USERNAME = config.getProperty("api.username");
    public static final String PASSWORD = config.getProperty("api.password");

    public static final String CONNECTION_TIMEOUT = config.getProperty("api.connection.timeout");
    public static final String READ_TIMEOUT = config.getProperty("api.read.timeout");
    public static final String RESPONSE_TIMEOUT = config.getProperty("api.response.timeout");

    // Retry Configuration
    public static final String MAX_RETRY_COUNT = config.getProperty("test.retry.count");
    public static final String RETRY_ENABLED = config.getProperty("test.retry.enabled");

    // Test Data
    public static final String TEST_DATA_PATH = config.getProperty("test.data.path");

    // Logging
    public static final String DEBUG_MODE = config.getProperty("test.debug.mode");
    public static final String LOG_REQUESTS = config.getProperty("api.log.requests");
    public static final String LOG_RESPONSES = config.getProperty("api.log.responses");
}


