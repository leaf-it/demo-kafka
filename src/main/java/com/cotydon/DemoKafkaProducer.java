package com.cotydon;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;

public class DemoKafkaProducer {
    public static void main(String[] args) throws FileNotFoundException {
       new  DemoKafkaProducer ().kafkaProcuder();
    }
    public void kafkaProcuder() {
        Properties props = new Properties();
        try {
            props.load(DemoKafkaProducer.class.getClassLoader().getResourceAsStream("producer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Producer<String, String> producer = new KafkaProducer(props, new StringSerializer(), new StringSerializer());
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("demo2", "DemoKafkaProducer", sc.next());
            System.out.println("end start message " + System.currentTimeMillis());
            Future<RecordMetadata> future =
                    producer.send(record, new Callback() {
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if (e != null) {
                                System.out.println("出问题了~");
                                System.out.println(e.getMessage());
                                e.printStackTrace();
                            } else {
                            }
                        }
                    });

            System.out.println("任务是否做完" + future.isDone());
            try {
                System.out.println("任务信息为：" + future.get(10, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                System.out.println("任务信息中端为："  );
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("任务信息为执行异常" );
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("任务信息为TimeoutException异常" );
                e.printStackTrace();
            }
            System.out.println("end send message " + System.currentTimeMillis());

        }
    }


}
