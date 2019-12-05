package leongu.mykafka.examples.demo;

import leongu.mykafka.examples.util.Constants;

public class KafkaConsumerProducerDemo {
    public static void main(String[] args) throws InterruptedException {
        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
        Producer producerThread = new Producer(Constants.TOPIC1, isAsync);
        producerThread.start();
        System.out.println("--------- start producer!");

        Consumer consumerThread = new Consumer(Constants.TOPIC1);
        consumerThread.start();
        System.out.println("--------- start consumer!");

        producerThread.join();
        consumerThread.join();
    }
}
