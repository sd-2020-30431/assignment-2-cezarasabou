package wasteless.server.presentation.dto;

import java.time.LocalDate;

public class WasteCalculatorDTO {
    private LocalDate calculationDate;
    private Integer burndownRateGoal;
    private String groceryListName;
    private Integer wasteResult;

    public Integer getWasteResult() {
        return wasteResult;
    }

    public void setWasteResult(Integer wasteResult) {
        this.wasteResult = wasteResult;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(LocalDate calculationDate) {
        this.calculationDate = calculationDate;
    }

    public Integer getBurndownRateGoal() {
        return burndownRateGoal;
    }

    public void setBurndownRateGoal(Integer burndownRateGoal) {
        this.burndownRateGoal = burndownRateGoal;
    }

    public String getGroceryListName() {
        return groceryListName;
    }

    public void setGroceryListName(String groceryListName) {
        this.groceryListName = groceryListName;
    }
}
