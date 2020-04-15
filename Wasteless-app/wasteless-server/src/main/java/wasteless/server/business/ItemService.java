package wasteless.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.model.Item;
import wasteless.server.persistance.GroceryListRepository;
import wasteless.server.persistance.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final GroceryListRepository groceryListRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, GroceryListRepository groceryListRepository) {
        this.itemRepository = itemRepository;
        this.groceryListRepository = groceryListRepository;
    }

    public List<Item> getAllItems(Long groceryListId) {
        GroceryList groceryList = groceryListRepository.getOne(groceryListId);
        return groceryList.getGroceryItems();
    }

    public Item getItemById(Long groceryListId, Long itemId) throws ResourceNotFoundException{

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));
        GroceryList groceryList = groceryListRepository.getOne(groceryListId);
        item.setGroceryList(groceryList);

        return item;
    }

    public Item createItem(Long groceryListId,Item item) {
        GroceryList groceryList = groceryListRepository.getOne(groceryListId);
        item.setGroceryList(groceryList);
        return itemRepository.save(item);
    }

    public Item updateItem(Long groceryListId, Long itemId, Item itemDetails) throws ResourceNotFoundException{
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        item.setItemName(itemDetails.getItemName());
        item.setCalorieValue(itemDetails.getCalorieValue());
        item.setExpirationDate(itemDetails.getExpirationDate());
        item.setConsumptionDate(itemDetails.getConsumptionDate());
        item.setPurchaseDate(itemDetails.getPurchaseDate());
        item.setQuantity(itemDetails.getQuantity());

        GroceryList groceryList = groceryListRepository.getOne(groceryListId);
        item.setGroceryList(groceryList);

        final Item updatedItem = itemRepository.save(item);
        return updatedItem;
    }

    public void deleteItem(Long itemId) throws ResourceNotFoundException{
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        itemRepository.delete(item);
    }

}
