package wasteless.server.presentation.mapper;

import org.springframework.stereotype.Component;
import wasteless.server.model.GroceryList;
import wasteless.server.model.User;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.dto.UserDTO;

@Component
public class GroceryListMapper {
    public GroceryListDTO convertToDTO(GroceryList groceryList){
        GroceryListDTO groceryListDTO = new GroceryListDTO();

        groceryListDTO.setGroceryListId(groceryList.getId());
        groceryListDTO.setGroceryListName(groceryList.getGroceryListName());
        groceryListDTO.setUserId(groceryList.getUser().getId());

        return groceryListDTO;
    }
}
