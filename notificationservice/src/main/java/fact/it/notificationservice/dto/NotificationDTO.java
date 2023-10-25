package fact.it.notificationservice.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private String id;
    private String message;
    private String userId;
}