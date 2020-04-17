package wasteless.server.presentation;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import wasteless.server.presentation.notification.ReportMessage;
import wasteless.server.presentation.notification.ReportNotification;

@Controller
public class NotificationController {


    @MessageMapping("/report")
    @SendTo("/topic/reports")
    public ReportNotification reportNotification(ReportMessage reportMessage) throws Exception{
        Thread.sleep(1000);
        //aici pun ulterior fix mesajul generat din report factory
        return new ReportNotification("incercare!");
    }
}
