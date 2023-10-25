package fact.it.notificationservice.controller;

import fact.it.notificationservice.dto.NotificationDTO;
import fact.it.notificationservice.model.Notification;
import fact.it.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping
    public NotificationDTO createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }
}