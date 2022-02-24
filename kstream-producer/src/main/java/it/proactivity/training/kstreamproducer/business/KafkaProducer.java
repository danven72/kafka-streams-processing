package it.proactivity.training.kstreamproducer.business;

import it.proactivity.training.kafkastream.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Configuration
public class KafkaProducer {

    private final static List<String> destinationsList = Arrays.asList("ITALY", "FRANCE", "SPAIN");
    private final static Random random = new Random();
/*
    @Bean
    public Supplier<Flux<Long>> numberProducer() {
        return () -> Flux.range(1, 1000)
                .map(i -> (long) i)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
*/

    @Bean
    public Supplier<Flux<Message<Order>>> orderProducer() {
        return () -> Flux.range(1, 100)
                .map(i -> buildMessage(i))
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    private Message<Order> buildMessage(int i) {
        String destination = destinationsList.get(random.nextInt(destinationsList.size()));
        Order order = new Order((long) i,"Order_"+i, 30.55d+i, destination, 0d);
        return MessageBuilder.withPayload(order)
                //.setHeader(KafkaHeaders.MESSAGE_KEY, "TEST")
                .build();
    }
}
