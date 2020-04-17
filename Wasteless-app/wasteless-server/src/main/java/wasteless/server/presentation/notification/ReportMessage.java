package wasteless.server.presentation.notification;

public class ReportMessage {
    private String message;

    public ReportMessage(){

    }
    public ReportMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
