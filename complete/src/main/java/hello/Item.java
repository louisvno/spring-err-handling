package hello;

public class Item {
    private String name;
    private User owner;
    private Integer id;

    public Item(String name, User owner,Integer id) {
        this.name = name;
        this.owner = owner;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public Integer getId() {
        return id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
