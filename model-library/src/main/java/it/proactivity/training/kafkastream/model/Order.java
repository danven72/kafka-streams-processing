package it.proactivity.training.kafkastream.model;

import java.util.Objects;

public class Order {
    private Long id;
    private String descritpion;
    private Double price;
    private String destination;
    private Double taxes;

    public Order() {
    }

    public Order(Long id, String descritpion, Double price, String destination, Double taxes) {
        this.id = id;
        this.descritpion = descritpion;
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

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
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
        return Objects.equals(getId(), order.getId()) && Objects.equals(getDescritpion(), order.getDescritpion()) && Objects.equals(getPrice(), order.getPrice()) && Objects.equals(getDestination(), order.getDestination()) && Objects.equals(getTaxes(), order.getTaxes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescritpion(), getPrice(), getDestination(), getTaxes());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", descritpion='" + descritpion + '\'' +
                ", price=" + price +
                ", destination='" + destination + '\'' +
                ", taxes=" + taxes +
                '}';
    }
}
