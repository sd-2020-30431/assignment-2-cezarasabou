package wasteless.server.business;

import org.springframework.stereotype.Service;
import wasteless.server.model.GroceryList;
import wasteless.server.model.Item;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class WasteManagerService {

    public int getTotalCalories(LocalDate calculationDay, GroceryList groceryList) {

        int totalCalories = 0;
        float calorieRate = 0;

        for(Item item : groceryList.getGroceryItems()){

            calorieRate = item.getCalorieValue()/item.getDaysUntilExpiration(calculationDay);
            totalCalories += calorieRate;
        }
        return totalCalories;
    }
}
