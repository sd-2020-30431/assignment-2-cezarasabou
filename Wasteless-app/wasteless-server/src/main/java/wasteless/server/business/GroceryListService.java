package wasteless.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.model.User;
import wasteless.server.persistance.GroceryListRepository;
import wasteless.server.persistance.UserRepository;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.mapper.GroceryListMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryListService {

    private final GroceryListRepository groceryListRepository;
    private final UserRepository userRepository;
    private final GroceryListMapper groceryListMapper;

    @Autowired
    public GroceryListService(GroceryListRepository groceryListRepository, UserRepository userRepository, GroceryListMapper groceryListMapper) {
        this.groceryListRepository = groceryListRepository;
        this.userRepository = userRepository;
        this.groceryListMapper = groceryListMapper;
    }

    public List<GroceryList> getAllGroceryLists(Long userId) {
        User user = userRepository.getOne(userId);
        return user.getGroceryLists();
    }

    public GroceryList getGroceryListById(Long userId, Long groceryListId) throws ResourceNotFoundException{

        //aici iar nu stiu cum sa caut lista dupa id-ul ei si al userului...
        //adica e functia find by id data de repo, eu nu am acces la ea
        //stiu ca din user pot gasi toate listele userului
        User user = userRepository.getOne(userId);

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + groceryListId));

        return groceryList;
    }

    public GroceryList createGroceryList(Long userId, String groceryListName){
        GroceryList groceryList = new GroceryList(groceryListName);
        User user = userRepository.getOne(userId);
        groceryList.setUser(user);
        return groceryListRepository.save(groceryList);
    }

    public GroceryList updateGroceryList(Long userId,Long groceryListId, GroceryList groceryListDetails) throws ResourceNotFoundException{

        //aici iar nu stiu cum sa caut lista dupa id-ul ei si al userului...
        User user = userRepository.getOne(userId);

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + groceryListId));


        groceryList.setGroceryListName(groceryListDetails.getGroceryListName());
        groceryList.setGroceryItems(groceryListDetails.getGroceryItems());

        final GroceryList updatedGroceryList = groceryListRepository.save(groceryList);
        return groceryList;
    }

    public void deleteGroceryList(Long userId, Long groceryListId) throws ResourceNotFoundException{

        //aici iar nu stiu cum sa caut lista dupa id-ul ei si al userului...
        User user = userRepository.getOne(userId);

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery list not found for this id :: " + groceryListId));

        groceryListRepository.delete(groceryList);
    }
}
