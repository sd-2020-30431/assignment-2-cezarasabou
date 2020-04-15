package wasteless.server.presentation.dto;

public class GroceryListDTO {
    private long groceryListId;
    private String groceryListName;
    private long userId;



    public long getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(long groceryListId) {
        this.groceryListId = groceryListId;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
