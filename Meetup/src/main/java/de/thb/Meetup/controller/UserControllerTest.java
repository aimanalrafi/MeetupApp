package de.thb.Meetup.controller;

import de.thb.Meetup.dto.*;
import de.thb.Meetup.dto.UserNoPasswordDto;
import de.thb.Meetup.entity.User;
import de.thb.Meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/user")
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<EntityModel<UserNoPasswordDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<UserNoPasswordDto> userNoPasswordDtoList = users
                .stream()
                .map(UserControllerTest::mapToUserNoPasswordDto
                )
                .collect(Collectors.toList());

        List<EntityModel<UserNoPasswordDto>> entityModels = new ArrayList<>();

        for(UserNoPasswordDto userNoPasswordDto : userNoPasswordDtoList) {
            entityModels.add(EntityModel.of(userNoPasswordDto));
        }

        return entityModels;
    }

    @GetMapping("/{id}")
    public EntityModel<UserDto> getById(@PathVariable("id") Long id) {
        Optional<User> userOpt = userService.getUserById(id);

        if (userService.getUserById(id).isPresent()) {
            UserDto userDto = userOpt.map(UserControllerTest::mapToUserDto
            ).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
            );
            return EntityModel.of(userDto);


        } else {
            //könnte man noch einkürzen mit orElseThrow
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody ActionUserDto actionUserDto) {
        User createdUser = userService.addUser(actionUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    //
    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody ActionUserDto actionUserDto) {
        User updatedUser = userService.updateUser(userId, actionUserDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    //
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/join-event-{eventId}")
    public ResponseEntity<User> joinEvent(@PathVariable("id") Long userId, @PathVariable("eventId") Long eventId) {
        boolean succesful = userService.joinEvent(userId, eventId);
        if(succesful){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event is full or user is already a participant!");
        }

    }





    public static UserNoPasswordDto mapToUserNoPasswordDto(User user) {
        return UserNoPasswordDto.builder()
                .id(user.getId())
                .username(user.getUsername())
//                .displayName(user.getDisplayName())
                .email(user.getEmail())
//                .password(user.getPassword())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
