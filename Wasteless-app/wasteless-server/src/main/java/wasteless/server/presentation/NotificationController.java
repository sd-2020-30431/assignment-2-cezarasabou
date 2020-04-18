package wasteless.server.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import wasteless.server.business.WasteManagerService;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

import javax.annotation.PostConstruct;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class NotificationController implements PropertyChangeListener {

    private final SimpMessagingTemplate template;
    private final WasteManagerService wasteManagerService;

    @Autowired
    NotificationController(SimpMessagingTemplate template, WasteManagerService wasteManagerService) {
        this.template = template;
        this.wasteManagerService = wasteManagerService;
    }

    @PostConstruct
    public void subscribeToWasterService() {
        wasteManagerService.addPropertyChangeListener(this);
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(String message) {
        this.template.convertAndSend("/chat",
                    new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        WasteCalculatorDTO wasteCalculatorDTO = (WasteCalculatorDTO) propertyChangeEvent.getNewValue();
        int exceededValue = wasteCalculatorDTO.getWasteResult() - wasteCalculatorDTO.getBurndownRateGoal();

        String message = "For the list " + wasteCalculatorDTO.getGroceryListName() +
                "on the date " + wasteCalculatorDTO.getCalculationDate() +
                "the waste has exceeded the limits by " + exceededValue +
                "\n" + "You can try donating the excess food to the local orphanages " +
                "and the homeless centers in your city!";

        this.template.convertAndSend("/chat", message);
    }
}
