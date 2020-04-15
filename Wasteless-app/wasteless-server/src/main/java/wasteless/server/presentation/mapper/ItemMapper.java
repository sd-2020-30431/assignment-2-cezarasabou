package wasteless.server.presentation.mapper;

import org.springframework.stereotype.Component;
import wasteless.server.model.Item;
import wasteless.server.presentation.dto.ItemDTO;

@Component
public class ItemMapper {
    public ItemDTO convertToDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setItemId(item.getId());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setCalorieValue(item.getCalorieValue());
        itemDTO.setPurchaseDate(item.getPurchaseDate());
        itemDTO.setConsumptionDate(item.getConsumptionDate());
        itemDTO.setExpirationDate(item.getExpirationDate());

        return itemDTO;
    }
}
