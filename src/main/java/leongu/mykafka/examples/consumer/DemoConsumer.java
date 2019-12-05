package leongu.mykafka.examples.consumer;

import leongu.mykafka.examples.util.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class DemoConsumer {
  private static KafkaConsumer<Integer, String> initConsumer() {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER_URL + ":" + Constants.KAFKA_SERVER_PORT);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer2");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

    return new KafkaConsumer<>(props);
  }
  /**
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {
    KafkaConsumer<Integer, String> consumer = initConsumer();
    consumer.subscribe(Collections.singletonList(Constants.TOPIC1));
    ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
    for (ConsumerRecord<Integer, String> record : records) {
      System.out.println(record);
      System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
    }

    Thread.sleep(10000);
  }

}
