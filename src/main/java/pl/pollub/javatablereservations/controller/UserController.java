package pl.pollub.javatablereservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pollub.javatablereservations.dto.CreateUserDto;
import pl.pollub.javatablereservations.entity.User;
import pl.pollub.javatablereservations.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping(value = "/create_user")
    public void createUser(@RequestBody CreateUserDto createUserDto) {
        this.userService.saveUser(createUserDto);
    }

    @PostMapping(value = "/edit_user")
    public void editUser(@RequestBody CreateUserDto createUserDto) {
        this.userService.saveUser(createUserDto);
    }

}
