package it.proactivity.training.kstreamconsumer.business;

import it.proactivity.training.kafkastream.model.Order;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    private final static String ITALY_COURIER_EMAIL = "italy.courier@orders.com";
    private final static String FRENCH_COURIER_EMAIL = "french.courier@orders.com";
    private final static String SPAIN_COURIER_EMAIL = "spain.courier@orders.com";

    @Bean
    public Consumer<KStream<Long, Order>> ordersItalianConsumer() {
        return ordersConsumer(ITALY_COURIER_EMAIL);
    }

    @Bean
    public Consumer<KStream<Long, Order>> ordersFrenchConsumer() {
        return ordersConsumer(FRENCH_COURIER_EMAIL);
    }

    @Bean
    public Consumer<KStream<Long, Order>> ordersSpanishConsumer() {
        return ordersConsumer(SPAIN_COURIER_EMAIL);
    }

    private Consumer<KStream<Long, Order>> ordersConsumer(String courierEmail) {
        return strem -> strem.foreach((key, order) -> {
            final StringBuilder message = new StringBuilder("The order with id: ")
                    .append(order.getId())
                    .append(", destination: ")
                    .append(order.getDestination())
                    .append(", price: ")
                    .append(order.getPrice())
                    .append(", taxes: ")
                    .append(order.getTaxes())
                    .append(", total: ")
                    .append(order.getPrice()+order.getTaxes())
                    .append(" will sent to: ")
                    .append(courierEmail);
            System.out.println(message);
        });
    }

}
