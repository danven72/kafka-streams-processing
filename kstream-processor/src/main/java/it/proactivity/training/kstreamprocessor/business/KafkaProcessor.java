package it.proactivity.training.kstreamprocessor.business;

import it.proactivity.training.kafkastream.model.Order;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;

@Configuration
public class KafkaProcessor {

    /*
    @Bean
    public Function<KStream<String, Long>, KStream<String, Long>> evenNumberSquareProcessor() {
        return kStream -> kStream.filter((k, v) -> v % 2 == 0)
                .peek((k, v) -> System.out.println("Squaring Even: " + v))
                .mapValues(v -> v * v);
    }

     */

    @Bean
    public Function<KStream<Long, Order>, KStream<Long, Order>> orderItalianProcessor() {
        return kStream -> kStream.filter((k, o) -> "ITALY".equals(o.getDestination()))
                .peek((k, o) -> System.out.println("Italian Order selected: " + o))
                .mapValues(o -> o);
    }

}
