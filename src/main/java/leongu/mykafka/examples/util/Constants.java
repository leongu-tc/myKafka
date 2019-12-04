package leongu.mykafka.examples.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    // kafka config
    public static final String KAFKA_SERVER_URL = "kafka0";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String TOPIC1 = "topic1";
    public static final String TOPIC2 = "topic2";
    public static final String TOPIC3 = "topic3";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    // other
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Constants() {}

    public static long currTime() {
        return System.currentTimeMillis();
    }

    public static String currTimeStr() {
        return format.format(new Date());
    }
}
