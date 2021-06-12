package helixsystemupgrade.model;


import java.time.LocalDateTime;
import java.time.ZoneId;

public class Notification {
    private String notificationMessage;
    private Product notificationProduct;
    private LocalDateTime notificationDateTime;

    public Notification(String notificationMessage, Product notificationProduct) {
        this.notificationMessage = notificationMessage;
        this.notificationProduct = notificationProduct;
        this.notificationDateTime = getCurrentDateTime();
    }

    private LocalDateTime getCurrentDateTime() {
        return java.time.LocalDateTime.now(ZoneId.of("UTC"));
    }
}
