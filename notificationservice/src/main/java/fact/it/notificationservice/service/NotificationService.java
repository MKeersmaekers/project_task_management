package fact.it.notificationservice.service;

import fact.it.notificationservice.dto.NotificationDTO;
import fact.it.notificationservice.model.Notification;
import fact.it.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final WebClient webClient;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, WebClient webClient) {
        this.notificationRepository = notificationRepository;
        this.webClient = webClient;
    }

    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO createNotification(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setUserId(notification.getUserId());
        return notificationDTO;
    }
}