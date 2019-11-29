package leongu.mykafka.examples.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class HashPartitioner implements Partitioner {

  public int partition(String topic, Object key, byte[] keyBytes, Object value,
      byte[] valueBytes, Cluster cluster) {
    List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
    int numPartitions = partitions.size();
    if ((key instanceof Integer)) {
      return Math.abs(Integer.parseInt(key.toString())) % numPartitions;
    }
    return Math.abs(key.hashCode() % numPartitions);
  }

  public void close() {

  }

  public void configure(Map<String, ?> map) {

  }
}

