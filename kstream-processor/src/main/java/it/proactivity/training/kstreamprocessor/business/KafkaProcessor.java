package it.proactivity.training.kstreamprocessor.business;

import it.proactivity.training.kafkastream.model.Order;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Function<KStream<Long, Order>, KStream<Long, Order>> calculateItalianTaxProcessor() {
        return process("ITALY", 0.21d);
    }

    @Bean
    public Function<KStream<Long, Order>, KStream<Long, Order>> calculateFrenchProcessor() {
        return process("FRANCE", 0.19d);
    }

    @Bean
    public Function<KStream<Long, Order>, KStream<Long, Order>> calculateSpanishProcessor() {
        return process("SPAIN", 0.23d);
    }

    private Function<KStream<Long, Order>, KStream<Long, Order>> process(String country, double taxQuote) {
        return kStream -> kStream.filter((k, o) -> country.equals(o.getDestination()))
                .peek((k, o) -> {
                    System.out.println("Order from " + country + " selected: " + o);
                    o.setTaxes(o.getPrice() * taxQuote);
                })
                .mapValues(o -> o);
    }







}
