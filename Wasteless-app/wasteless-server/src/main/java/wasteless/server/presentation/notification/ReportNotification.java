package wasteless.server.presentation.notification;

public class ReportNotification {

    //aici e echivalentul de la greeting dar nu stiu de ce am nevoie de el
    private String content;

    public ReportNotification(){

    }
    public ReportNotification(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
