package helixsystemupgrade.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    Product testProduct = new Product("TestProduct", 0, "100", "testimg.png");

    @Test
    void testIsNotifcationDateFormatCorrect() {
        Notification notification = new Notification(123, "TestMessage", "TestHelix", testProduct);

        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        assertEquals(notification.getNotificationDateTime(), dateTime.format(formatter));
    }
}
