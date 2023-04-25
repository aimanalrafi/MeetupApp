package de.thb.Meetup.service;

import de.thb.Meetup.dto.ActionUserDto;
import de.thb.Meetup.dto.LoginUserDto;
import de.thb.Meetup.entity.Event;
import de.thb.Meetup.entity.User;
import de.thb.Meetup.repository.EventRepository;
import de.thb.Meetup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

//    public User getUserByIdTest(Long id) {return  userRepository.getUserByIdTestRep(id);}


    public User addUser(ActionUserDto actionUserDto) {
        return userRepository.save(User.builder()
//                .displayName(actionUserDto.getDisplayName())
                .username(actionUserDto.getUsername())
                .email(actionUserDto.getEmail())
                .password(actionUserDto.getPassword())
                .build());
    }

    public boolean joinEvent(Long userId, Long eventId){
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        Event event = eventOptional.get();
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        if((!event.getParticipants().contains(userOptional.get()) && (event.getParticipants().size() < event.getCapacity()))) {
            event.getParticipants().add(user);
            eventRepository.save(event);
            return true;
        } else {
            return false;
        }
    }

    public void deleteUserById(Long id){
        Optional<User> userOptional = getUserById(id);
        userRepository.delete(userOptional.get());
    }
    public User updateUser(Long userId, ActionUserDto actionUserDto){
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.save(User.builder()
                    .id(userId)
                    .username(actionUserDto.getUsername())
//                    .displayName(actionUserDto.getDisplayName())
                    .email(actionUserDto.getEmail())
                    .password(actionUserDto.getPassword())
                    .build());
        } else
            return null;
    }


    public User validateLogin(LoginUserDto loginUserDto) {
        Optional<User> optUser = userRepository.findUserByUsername(loginUserDto.getUsername());
        if(optUser.isPresent() && (Objects.equals(optUser.get().getPassword(), loginUserDto.getPassword()))){
            System.out.println("Validation complete!");
            return optUser.get();
        } else {
            return null;
        }

    }
}
