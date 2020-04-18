package wasteless.server.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate template;

    @Autowired
    NotificationController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(String message) {
        this.template.convertAndSend("/chat",
                    new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
    }
}
