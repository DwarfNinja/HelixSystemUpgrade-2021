package helixsystemupgrade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@JsonDeserialize(as = Notification.class)
public class Notification {
    private int notificationID;
    private String notificationMessage;
    private String notificationDateTime;
    private String helixSystemName;
    private Product notificationProduct;

    //TODO: Remove default constructor in place for JsonProperty annotated constructor
    public Notification() {
    }

    public Notification(int notificationID, String notificationMessage, String helixSystemName, Product notificationProduct) {
        this.notificationID = notificationID;
        this.notificationMessage = notificationMessage;
        this.notificationDateTime = getCurrentDateTime();
        this.helixSystemName = helixSystemName;
        this.notificationProduct = notificationProduct;
    }

    @JsonProperty("notificationID")
    public int getNotificationID() {
        return notificationID;
    }

    @JsonProperty("notificationMessage")
    public String getNotificationMessage() {
        return notificationMessage;
    }

    @JsonProperty("notificationDateTime")
    public String getNotificationDateTime() {
        return notificationDateTime;
    }

    @JsonProperty("helixSystemName")
    public String getHelixSystemName() {
        return helixSystemName;
    }

    @JsonProperty("notificationProduct")
    public Product getNotificationProduct() {
        return notificationProduct;
    }

    public String getCurrentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
}
