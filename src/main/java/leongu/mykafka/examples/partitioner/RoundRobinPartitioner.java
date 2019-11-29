package leongu.mykafka.examples.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class RoundRobinPartitioner implements Partitioner {
  private static AtomicLong next = new AtomicLong();

  public int partition(String topic, Object key, byte[] keyBytes, Object value,
      byte[] valueBytes, Cluster cluster) {
    List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
    int numPartitions = partitions.size();
    long nextIndex = next.incrementAndGet();
    return (int) nextIndex % numPartitions;
  }

  public void close() {
    next.set(0l);
  }

  public void configure(Map<String, ?> map) {

  }
}
