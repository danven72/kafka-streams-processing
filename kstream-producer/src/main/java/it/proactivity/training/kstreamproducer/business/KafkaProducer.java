package it.proactivity.training.kstreamproducer.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class KafkaProducer {

    @Bean
    public Supplier<Flux<Long>> numberProducer() {
        return () -> Flux.range(1, 1000)
                .map(i -> (long) i)
                .delayElements(Duration.ofSeconds(1));
    }
}
