package leongu.mykafka.examples.producer;

import leongu.mykafka.examples.util.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 注意，如果是 docker compose的集群，注意 127.0.0.1  kafka0 这样的映射在 hosts里面不要丢了
 */
public class ProducerDemo {
  static final String BROKER_LIST =
      Constants.KAFKA_SERVER_URL + ":" + Constants.KAFKA_SERVER_PORT;
  static final String TOPIC = Constants.TOPIC1;

  public static void main(String[] args) throws Exception {
    KafkaProducer<Integer, String> producer = initProducer();
    sendOne(producer, TOPIC);
  }

  private static KafkaProducer<Integer, String> initProducer() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        IntegerSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    // props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, HashPartitioner.class.getName());

    KafkaProducer<Integer, String> producer =
        new KafkaProducer<Integer, String>(props);
    return producer;
  }

  public static void sendOne(KafkaProducer<Integer, String> producer,
      String topic) throws InterruptedException {
    ProducerRecord<Integer, String> record1 =
        new ProducerRecord<Integer, String>(topic, 31, "test " + Constants.currTimeStr());
    producer.send(record1);
    Thread.sleep(1000);
    ProducerRecord<Integer, String> record2 =
        new ProducerRecord<Integer, String>(topic, 32, "test " + Constants.currTimeStr());
    producer.send(record2);
    Thread.sleep(1000);
    ProducerRecord<Integer, String> record3 =
        new ProducerRecord<Integer, String>(topic, 33, "test " + Constants.currTimeStr());
    producer.send(record3);
    Thread.sleep(1000);
    ProducerRecord<Integer, String> record4 =
        new ProducerRecord<Integer, String>(topic, 34, "test " + Constants.currTimeStr());
    producer.send(record4);
    Thread.sleep(1000);
    producer.close();
  }

}