package org.example.microservicea.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class ControllerKafka {
    public KafkaProducer<String, String> kafkaProducer; //may be in service

    public ControllerKafka() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(properties);
    }

    @PostMapping("/producer")
    public ResponseEntity<HttpStatus> sendMessageKafka(@RequestBody String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("user_topic", message);
        kafkaProducer.send(producerRecord);
        return new ResponseEntity<>(HttpStatus.OK); //only for example
    }
}
