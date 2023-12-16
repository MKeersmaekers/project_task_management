package fact.it.userservice;

import fact.it.userservice.dto.UserDTO;
import fact.it.userservice.model.User;
import fact.it.userservice.repository.UserRepository;
import fact.it.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserserviceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1L, "user1", "user1@example.com");
        User user2 = new User(2L, "user2", "user2@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals(user1.getUserId(), result.get(0).getUserId());
        assertEquals(user2.getUsername(), result.get(1).getUsername());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "newuser", "newuser@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userService.createUser(user);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUser() {
        Long userId = 1L;
        User user = new User(userId, "existinguser", "existinguser@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUser(userId);

        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUser_NotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUser(userId));

        verify(userRepository, times(1)).findById(userId);
    }
}
