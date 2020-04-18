package wasteless.server.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wasteless.server.business.ExportService;
import wasteless.server.business.GroceryListService;
import wasteless.server.business.WasteManagerService;
import wasteless.server.model.GroceryList;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.dto.WasteCalculatorDTO;
import wasteless.server.presentation.mapper.GroceryListMapper;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GroceryListController {

    private final GroceryListService groceryListService;
    private final GroceryListMapper groceryListMapper;
    private final WasteManagerService wasteManagerService;
    private final ExportService exportService;

    public GroceryListController(GroceryListService groceryListService,
                                 GroceryListMapper groceryListMapper,
                                 WasteManagerService wasteManagerService,
                                 ExportService exportService) {
        this.groceryListService = groceryListService;
        this.groceryListMapper = groceryListMapper;
        this.wasteManagerService = wasteManagerService;
        this.exportService = exportService;
    }

    //aici am ramas sa fac o functie care converteste cu mapperul din lista in dto
    @GetMapping("{userId}/groceryLists")
    public List<GroceryListDTO> getAllGroceryLists( @PathVariable(value = "userId") Long userId) {
        return groceryListService.getAllGroceryLists(userId)
                .stream()
                .map(groceryListMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("{userId}/groceryList/{id}")
    public ResponseEntity<GroceryListDTO> getGroceryListById(@PathVariable(value = "id") Long groceryListId,
                                                             @PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException {
        GroceryList groceryList = groceryListService.getGroceryListById(userId,groceryListId);
        return ResponseEntity.ok().body(groceryListMapper.convertToDTO(groceryList));
    }

    @PostMapping("{userId}/createGroceryList")
    public GroceryListDTO createGroceryList(@PathVariable(value = "userId") Long userId,
                                            @Valid @RequestBody String groceryListName) {
        GroceryList groceryList = groceryListService.createGroceryList(userId,groceryListName);
        return groceryListMapper.convertToDTO(groceryList);
    }

    //public ceva calculaterates(@PathVariable(value="userId") Long userId,@Valid @RequestBody ceva )

    @PostMapping("{userId}/{groceryListId}")
    public WasteCalculatorDTO calculateWaste(@PathVariable(value = "userId") Long userId,
                                             @PathVariable(value = "groceryListId") Long groceryListId,
                                             @Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO) throws ResourceNotFoundException{

        return wasteManagerService.computeWasteDTO(userId, groceryListId, wasteCalculatorDTO);
    }

    @PutMapping("{userId}/updateGroceryListItem/{id}")
    public ResponseEntity<GroceryListDTO> updateGroceryList(@PathVariable(value = "userId") Long userId,
                                                         @PathVariable(value = "id") Long groceryListId,
                                                         @Valid @RequestBody GroceryList groceryListDetails) throws ResourceNotFoundException {

        final GroceryList updatedGroceryList =
                groceryListService.updateGroceryList(userId,groceryListId,groceryListDetails);
        return ResponseEntity.ok(groceryListMapper.convertToDTO(updatedGroceryList));
    }

    @DeleteMapping("{userId}/deleteGroceryList/{id}")
    public Map<String, Boolean> deleteGroceryList(@PathVariable(value = "id") Long groceryListId,
                                                  @PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException {
        groceryListService.deleteGroceryList(userId,groceryListId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("textFile")
    public void generateTextReport(@Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO) {
        exportService.exportTextWasteReport(wasteCalculatorDTO);
    }


    @PostMapping("jsonFile")
    public void generateJsonReport(@Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO){
        exportService.exportJsonWasteReport(wasteCalculatorDTO);
    }
}
