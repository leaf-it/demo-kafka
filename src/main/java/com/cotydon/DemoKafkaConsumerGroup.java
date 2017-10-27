package com.cotydon;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DemoKafkaConsumerGroup {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(DemoKafkaConsumerGroup.class.getClassLoader().getResourceAsStream("consumer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        KafkaConsumer consumer = new KafkaConsumer<String, String>(props, new StringDeserializer(), new StringDeserializer());
        consumer.subscribe(Arrays.asList("demo"));
        while (true) {
            //每次取100条信息
            ConsumerRecords<String, String> records = consumer.poll(100);

            for (ConsumerRecord<String, String> record : records)
                if(records.count()!=0) {
                    System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                    System.out.println(records.count());
                }

        }
    }
}
