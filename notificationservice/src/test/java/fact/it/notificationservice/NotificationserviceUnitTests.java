package fact.it.notificationservice;

import fact.it.notificationservice.dto.NotificationDTO;
import fact.it.notificationservice.model.Notification;
import fact.it.notificationservice.repository.NotificationRepository;
import fact.it.notificationservice.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotificationserviceUnitTests {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNotifications() {
        Notification notification1 = new Notification("1", "Message 1", "user1");
        Notification notification2 = new Notification("2", "Message 2", "user2");
        when(notificationRepository.findAll()).thenReturn(Arrays.asList(notification1, notification2));

        List<NotificationDTO> result = notificationService.getAllNotifications();

        assertEquals(2, result.size());
        assertEquals(notification1.getId(), result.get(0).getId());
        assertEquals(notification2.getMessage(), result.get(1).getMessage());

        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    void testCreateNotification() {
        Notification newNotification = new Notification(null, "New Message", "newuser");
        when(notificationRepository.save(any(Notification.class))).thenReturn(newNotification);

        NotificationDTO result = notificationService.createNotification(newNotification);

        assertEquals(newNotification.getId(), result.getId());
        assertEquals(newNotification.getMessage(), result.getMessage());
        assertEquals(newNotification.getUserId(), result.getUserId());

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
}
