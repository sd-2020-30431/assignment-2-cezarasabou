package wasteless.server.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wasteless.server.business.ItemService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.Item;
import wasteless.server.presentation.dto.ItemDTO;
import wasteless.server.presentation.mapper.ItemMapper;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/{groceryListId}/items")
    public List<ItemDTO> getAllItems(@PathVariable("groceryListId") Long groceryListId) {
        return itemService.getAllItems(groceryListId)
                .stream()
                .map(itemMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{groceryListId}/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(value = "groceryListId") Long groceryListId,
                                               @PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException {
       Item item = itemService.getItemById(groceryListId,itemId);
        return ResponseEntity.ok().body(itemMapper.convertToDTO(item));
    }

    @PostMapping("/{groceryListId}/createItem")
    public ItemDTO createItem(@PathVariable(value = "groceryListId") Long groceryListId,@Valid @RequestBody Item item) {
        return itemMapper.convertToDTO(itemService.createItem(groceryListId, item));
    }

    @PutMapping("/{groceryListId}/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable(value = "id") Long itemId,
                                              @PathVariable(value = "groceryListId") Long groceryListId,
                                           @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {


        final Item updatedItem = itemService.updateItem(groceryListId,itemId,itemDetails);
        return ResponseEntity.ok(itemMapper.convertToDTO(updatedItem));
    }

    @GetMapping("/deleteItem/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException {
        itemService.deleteItem(itemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
