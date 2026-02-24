package com.praful.notificationservice.listener;

import com.praful.notificationservice.event.EmployeeCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventListener
{

    private static final Logger log = LoggerFactory.getLogger(EmployeeEventListener.class);
    @KafkaListener(topics = "employee-topic")
    public void consume(EmployeeCreatedEvent event) {

        log.info("Received oEmployeeCreatedEvent: {}", event);
        if (event.getEmail() == null)
        {
            throw new RuntimeException("Email is null. Simulating failure.");
        }
        log.info("Sending email to: {}", event.getEmail());
    }

    @KafkaListener(topics = "employee-topic.DLT")
    public void listenDLT(EmployeeCreatedEvent event) {
        log.error("Message moved to DLT after retries: {}", event);
    }

}