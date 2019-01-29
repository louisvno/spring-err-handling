package hello;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class User {
    private int id;
    private String name;

    private List<Item> userItems;

    public User(int id, String name, List<Item> userItems) {
        this.id = id;
        this.name = name;
        this.userItems = userItems;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonSerialize(using= CustomListSerializer.class)
    public List<Item> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<Item> userItems) {
        this.userItems = userItems;
    }
}
