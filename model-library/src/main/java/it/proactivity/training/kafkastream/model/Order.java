package it.proactivity.training.kafkastream.model;

import java.util.Objects;

public class Order {
    private Long id;
    private String description;
    private Double price;
    private String destination;
    private Double taxes;

    public Order() {
    }

    public Order(Long id, String description, Double price, String destination, Double taxes) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.destination = destination;
        this.taxes = taxes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getDescription(), order.getDescription()) && Objects.equals(getPrice(), order.getPrice()) && Objects.equals(getDestination(), order.getDestination()) && Objects.equals(getTaxes(), order.getTaxes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getPrice(), getDestination(), getTaxes());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", destination='" + destination + '\'' +
                ", taxes=" + taxes +
                '}';
    }
}
