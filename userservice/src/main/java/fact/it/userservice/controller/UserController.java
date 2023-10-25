package fact.it.userservice.controller;

import fact.it.userservice.dto.UserDTO;
import fact.it.userservice.model.User;
import fact.it.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}