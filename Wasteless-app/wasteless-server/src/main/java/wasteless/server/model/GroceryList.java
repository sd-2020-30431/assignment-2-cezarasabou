package wasteless.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grocery_list")
public class GroceryList {
    public GroceryList() {
    }

    public GroceryList(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "list_name", nullable = false)
    private String groceryListName;
    @OneToMany(mappedBy = "groceryList", cascade = CascadeType.ALL)
    private List<Item> groceryItems;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(List<Item> groceryItems) {
        this.groceryItems = groceryItems;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    public void addGroceryItem(Item newItem){
        groceryItems.add(newItem);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "id=" + id +
                ", groceryListName='" + groceryListName + '\'' +
                ", groceryItems=" + groceryItems +
                '}';
    }
}
