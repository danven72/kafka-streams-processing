package it.proactivity.training.kafkastream.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Destination {
    public enum Country {
        ITALY, FRANCE, SPAIN;
    }
    private final static List<Country> countries = Arrays.asList(Country.values());
    private final static Random random = new Random();

    public static String getRandomDestination() {
        return countries.get(random.nextInt(countries.size())).name();
    }

}
