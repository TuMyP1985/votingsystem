package ru.java.votingsystem.to;

public class RestaurantTo {

    private final Integer id;

    private final String name;

    private final boolean vote;

    public RestaurantTo(Integer id, String name, boolean vote) {
        this.id = id;
        this.name = name;
        this.vote = vote;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVote() {
        return vote;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                ", vote=" + vote +
                '}';
    }
}
