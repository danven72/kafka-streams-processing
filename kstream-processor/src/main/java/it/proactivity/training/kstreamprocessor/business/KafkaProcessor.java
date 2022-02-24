package it.proactivity.training.kstreamprocessor.business;

import it.proactivity.training.kafkastream.model.Destination;
import it.proactivity.training.kafkastream.model.Order;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class KafkaProcessor {

    private final static Double ITALY_TAX_QUOTE = 0.21d;
    private final static Double FRANCE_TAX_QUOTE = 0.19d;
    private final static Double SPAIN_TAX_QUOTE = 0.17d;

    @Bean
    public Function<KStream<Long, Order>, KStream<Long, Order>> calculateItalianTaxProcessor() {
        return process(Destination.Country.ITALY.name(), ITALY_TAX_QUOTE);
    }

    @Bean
    public Function<KStream<Long, Order>, KStream<Long, Order>> calculateFrenchProcessor() {
        return process(Destination.Country.FRANCE.name(), FRANCE_TAX_QUOTE);
    }

    @Bean
    public Function<KStream<Long, Order>, KStream<Long, Order>> calculateSpanishProcessor() {
        return process(Destination.Country.SPAIN.name(), SPAIN_TAX_QUOTE);
    }

    private Function<KStream<Long, Order>, KStream<Long, Order>> process(String country, double taxQuote) {
        return kStream -> kStream.filter((k, o) -> country.equals(o.getDestination()))
                .peek((k, o) -> {
                    System.out.print("Order from " + country + " selected: " + o);
                })
                .mapValues(o -> {
                    o.setTaxes(o.getPrice() * taxQuote);
                    System.out.println(" - Tax calculated:" + o.getTaxes());
                    return o;
                });
    }

}
