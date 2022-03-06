package ru.java.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote u WHERE u.id=:id"),
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT u FROM Vote u ORDER BY u.id"),
})
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity{

    public static final String DELETE = "Vote.delete";
    public static final String ALL_SORTED = "Vote.getAllSorted";

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private Date registered = new Date();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Vote() {
    }

    public Vote(Integer id, Date registered, Restaurant restaurant, User user) {
        super(id);
        this.registered = registered;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", registered=" + registered +
                ", restaurant='" + restaurant +
                ", user=" + user +
                '}';
    }
}
