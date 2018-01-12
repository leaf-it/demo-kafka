package com.cotydon;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {
    @Value("${saas.translation.enable:false}")
    String startTranslationFlag;
    @Value("${saas.translation.bootstrap.servers}")
    String kafkaServer;
    @Value("${saas.translation.key.deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
    String keyDeserializer;
    @Value("${saas.translation.value.deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
    String valueDeserializer;
    @Value("${saas.translation.deserializer.encoding:utf-8}")
    String  deserializerEncoding;
    @Value("${saas.translation.enable.auto.commit:false}")
    String autoCommit;
    @Value("${saas.translation.auto.offset.reset:latest}")
    String offSet;

    public String getStartTranslationFlag() {
        return startTranslationFlag;
    }

    public void setStartTranslationFlag(String startTranslationFlag) {
        this.startTranslationFlag = startTranslationFlag;
    }

    public String getKafkaServer() {
        return kafkaServer;
    }

    public void setKafkaServer(String kafkaServer) {
        this.kafkaServer = kafkaServer;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public String getDeserializerEncoding() {
        return deserializerEncoding;
    }

    public void setDeserializerEncoding(String deserializerEncoding) {
        this.deserializerEncoding = deserializerEncoding;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getOffSet() {
        return offSet;
    }

    public void setOffSet(String offSet) {
        this.offSet = offSet;
    }

}
