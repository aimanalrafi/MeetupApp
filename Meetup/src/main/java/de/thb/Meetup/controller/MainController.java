package de.thb.Meetup.controller;

import de.thb.Meetup.dto.LoginUserDto;
import de.thb.Meetup.entity.User;
import de.thb.Meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<User> validateLoginDetails(@RequestBody LoginUserDto loginUserDto) {
        User userValidated = userService.validateLogin(loginUserDto);
        if(userValidated != null){
            return new ResponseEntity<>(userValidated, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "login details validation failed!");
        }
    }

}
