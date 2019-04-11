package nysa.nysa_20.model;

public class Messages {
    private String from, message;
    public Messages(String from, String message){
        this.from=from;
        this.message=message;

    }
    public Messages(){

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
