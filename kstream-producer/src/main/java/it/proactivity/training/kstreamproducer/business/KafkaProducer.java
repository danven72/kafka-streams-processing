package it.proactivity.training.kstreamproducer.business;

import it.proactivity.training.kafkastream.model.Destination;
import it.proactivity.training.kafkastream.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Configuration
public class KafkaProducer {

    @Bean
    public Supplier<Flux<Message<Order>>> orderProducer() {
        return () -> Flux.range(1, 110)
                .map(i -> buildMessage(i))
                .delayElements(Duration.ofSeconds(2))
                .log();
    }

    private Message<Order> buildMessage(int i) {
        Order order = new Order((long) i,"Order_"+i, 30.55d+i, Destination.getRandomDestination(), 0d);
        return MessageBuilder.withPayload(order)
                //.setHeader(KafkaHeaders.MESSAGE_KEY, "TEST")
                .build();
    }
}
