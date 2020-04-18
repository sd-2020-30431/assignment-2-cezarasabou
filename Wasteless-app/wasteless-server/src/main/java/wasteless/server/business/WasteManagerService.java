package wasteless.server.business;

import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.model.Item;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

@Service
public class WasteManagerService  {

    private PropertyChangeSupport support;
    private final GroceryListService groceryListService;

    public WasteManagerService(GroceryListService groceryListService){
        this.groceryListService = groceryListService;
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void notifyWasteLevels(WasteCalculatorDTO wasteCalculatorDTO){

        support.firePropertyChange("event", null, wasteCalculatorDTO );
    }

    private int getTotalCalories(LocalDate calculationDay, GroceryList groceryList) {

        int totalCalories = 0;
        float calorieRate = 0;

        for(Item item : groceryList.getGroceryItems()){
            if(!item.isAlreadyExpired(calculationDay)){
                calorieRate = item.getCalorieValue()/item.getDaysUntilExpiration(calculationDay);
                totalCalories += calorieRate;
            }

        }
        return totalCalories;
    }

    public WasteCalculatorDTO computeWasteDTO(Long userId, Long groceryListId, WasteCalculatorDTO wasteCalculatorDTO)  throws ResourceNotFoundException {
        GroceryList groceryList = groceryListService.getGroceryListById(userId, groceryListId);
        int totalCalories = getTotalCalories(wasteCalculatorDTO.getCalculationDate(), groceryList);
        wasteCalculatorDTO.setWasteResult(totalCalories);
        wasteCalculatorDTO.setGroceryListName(groceryList.getGroceryListName());

        if (totalCalories > wasteCalculatorDTO.getBurndownRateGoal()) {
            notifyWasteLevels(wasteCalculatorDTO);
        }

        return wasteCalculatorDTO;
    }

}
