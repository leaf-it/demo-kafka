package com.cotydon;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DemoKafkaProducer {
    public static void main(String[] args) throws FileNotFoundException {
        Properties props = new Properties();
        try {
            props.load(DemoKafkaProducer.class.getClassLoader().getResourceAsStream("producer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Producer<String, String> producer = new KafkaProducer(props,new StringSerializer(),new StringSerializer());
        while(true) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("demo", "DemoKafkaProducer", "I am a message form DemoKafkaProducer!");
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        e.printStackTrace();
                    } else {
                        System.out.println(recordMetadata.offset());
                        System.out.println(recordMetadata.partition());
                        System.out.println(recordMetadata.timestamp());
                        System.out.println("hello,I am successor");
                    }
                }
            });

            try {
                Thread.sleep(1000);
                producer.close();            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
