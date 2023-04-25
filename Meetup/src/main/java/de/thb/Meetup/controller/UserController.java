//package de.thb.Meetup.controller;
//
//import de.thb.Meetup.controller.form.EventFormModel;
//import de.thb.Meetup.controller.form.UserFormModel;
//import de.thb.Meetup.entity.Category;
//import de.thb.Meetup.entity.Event;
//import de.thb.Meetup.entity.User;
//import de.thb.Meetup.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//    @GetMapping("users")
//    public String showUsers(Model model){
//        model.addAttribute("users", userService.getAllUsers());
//
//        return "/layout/users";
//    }
//
//    @GetMapping("users/{id}")
//    public String showUserDetails(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        model.addAttribute("user", user);
//
//        return "/layout/user_actions";
//    }
//    @PostMapping("users")
//    public RedirectView addUser(UserFormModel userFormModel, RedirectAttributes redirectAttributes) {
//
//        User user = userService.addUser(
//                userFormModel.getDisplayName(),
//                userFormModel.getUsername(),
//                userFormModel.getEmail(),
//                userFormModel.getPassword());
//        if(user != null) {
//            redirectAttributes.addFlashAttribute("successUser", "User succesfully created!");
//
//        } else {
//            redirectAttributes.addFlashAttribute("errorUser", "User UN-succesfully created!");
//        }
//
//        return new RedirectView("/users");
//    }
//
//
//    @RequestMapping(value="/users/{id}", method= RequestMethod.POST, params="action=delete")
//    public RedirectView deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        Optional<User> optionalUser = userService.getUserById(id);
//        if(optionalUser.isPresent()){
//            //System.out.println("Event acquired!");
//            User userToDelete = optionalUser.get();
//            userService.deleteUser(userToDelete);
//            if(userToDelete == null)
//                redirectAttributes.addFlashAttribute("userDeleteSuccess", "User successfully deleted");
//
//        } else {
//            redirectAttributes.addFlashAttribute("userNotFound", "No user associated to id");
//        }
//
//
//        return new RedirectView("/users");
//    }
//
//    /**
//     * Can also directly use @PutMapping
//     * @param id
//     * @param redirectAttributes
//     * @return
//     */
//    @RequestMapping(value="/users/{id}", method= RequestMethod.POST, params="action=update")
//    public RedirectView updateUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
//                                    UserFormModel userFormModel){
//            userService.updateUser(
//                    id,
//                    userFormModel.getDisplayName(),
//                    userFormModel.getUsername(),
//                    userFormModel.getEmail(),
//                    userFormModel.getPassword());
//
//        return new RedirectView("/users/{id}");
//    }
//}
