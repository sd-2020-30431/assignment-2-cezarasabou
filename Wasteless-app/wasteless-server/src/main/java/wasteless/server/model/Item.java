package wasteless.server.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "item_name", nullable = false)
    private String itemName;
    @Column(name = "item_quantity", nullable = false)
    private Float quantity;
    @Column(name = "calorie_value", nullable = false)
    private Float calorieValue;
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Column(name = "consumption_date", nullable = false)
    private LocalDate consumptionDate;

    @ManyToOne
    @JoinColumn(name="groceryListId", nullable = false)
    private GroceryList groceryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getCalorieValue() {
        return calorieValue;
    }

    public void setCalorieValue(Float calorieValue) {
        this.calorieValue = calorieValue;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public GroceryList getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(GroceryList groceryList) {
        this.groceryList = groceryList;
    }

    public boolean isAlreadyExpired(LocalDate currentDate){
        return this.expirationDate.isBefore(currentDate);
    }

    public Integer getDaysUntilExpiration(LocalDate calculationDay){

        if(this.getExpirationDate().isAfter(calculationDay)){
            return (int) ChronoUnit.DAYS.between(calculationDay,this.getExpirationDate());
        }
        return 0;
    }

}
