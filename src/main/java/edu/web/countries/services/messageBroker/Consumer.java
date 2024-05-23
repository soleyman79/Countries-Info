package edu.web.countries.services.messageBroker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message) {
        System.out.println("Request received for " + message);
    }
}
