package ru.java.votingsystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish u WHERE u.id=:id"),
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT u FROM Dish u ORDER BY u.id"),
})
@Entity
@Table(name = "dishs")
public class Dish extends AbstractNamedEntity{

    public static final String DELETE = "Dish.delete";
    public static final String ALL_SORTED = "Dish.getAllSorted";

    @Column(name = "price", nullable = false)
    @Range(min = 1)
    private int price;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime registered = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Dish d) {
        this(d.id,d.name,d.price,d.restaurant);
    }

    public Dish(Integer id, String name, int price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", price='" + price +
                ", registered=" + registered +
                ", restaurant=" + restaurant +
                '}';
    }
}
