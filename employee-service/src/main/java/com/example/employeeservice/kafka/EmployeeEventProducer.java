package com.example.employeeservice.kafka;

import com.example.employeeservice.event.EmployeeCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EmployeeEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendEmployeeCreatedEvent(EmployeeCreatedEvent event) {
        System.out.println("Sending event to Kafka: " + event.getName());
        kafkaTemplate.send("employee-topic", event);
    }


}
