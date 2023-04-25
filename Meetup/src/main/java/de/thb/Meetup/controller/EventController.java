//package de.thb.Meetup.controller;
//
//import de.thb.Meetup.controller.form.EventFormModel;
//import de.thb.Meetup.entity.Category;
//import de.thb.Meetup.entity.Event;
//import de.thb.Meetup.service.CategoryService;
//import de.thb.Meetup.service.EventService;
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
//public class EventController {
//
//    @Autowired
//    private EventService eventService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping("events")
//    public String showEvents(Model model){
//        model.addAttribute("events", eventService.getAllEvents());
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("categories", categoryService.getAllCategories());
//
//        return "/layout/events";
//    }
//
//    @GetMapping("events/{id}")
//    public String showEventDetails(@PathVariable("id") Long id, Model model) {
//        Event event = eventService.getEventById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        model.addAttribute("event", event);
//        model.addAttribute("categories", categoryService.getAllCategories());
//
//        return "/layout/event_actions";
//    }
//
//    @PostMapping("events")
//    public RedirectView addEvent(EventFormModel eventFormModel, RedirectAttributes redirectAttributes,
//                                 @RequestParam("startRegistrationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startRegistrationDate,
//                                 @RequestParam("endRegistrationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endRegistrationDate,
//                                 @RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime eventDate) {
//
//        Event event = eventService.addEvent(eventFormModel.getTitle(),
//                eventFormModel.getDescription(),
//                eventFormModel.getCapacity(),
//                startRegistrationDate,
//                endRegistrationDate,
//                eventDate,
//                eventFormModel.getLocation(),
//                eventFormModel.getCategoryId(),
//                eventFormModel.getCreatorId());
//        if(event != null) {
//            redirectAttributes.addFlashAttribute("successEvent", "Event succesfully created!");
//
//        } else {
//            redirectAttributes.addFlashAttribute("errorEvent", "Event UN-succesfully created!");
//        }
//
//        return new RedirectView("/events");
//    }
//
//    @RequestMapping(value="/events/{id}", method= RequestMethod.POST, params="action=delete")
//    public RedirectView deleteEvent(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        Optional<Event> optionalEvent = eventService.getEventById(id);
//        if(optionalEvent.isPresent()){
//            //System.out.println("Event acquired!");
//            Event eventToDelete = optionalEvent.get();
//            eventService.deleteEvent(eventToDelete);
//            if(eventToDelete == null)
//                redirectAttributes.addFlashAttribute("eventDeleteSuccess", "Event successfully deleted");
//
//        } else {
//            redirectAttributes.addFlashAttribute("eventNotFound", "No event associated to id");
//        }
//
//
//       return new RedirectView("/events");
//    }
//
//    /**
//     * Can also directly use @PutMapping
//     * @param id
//     * @param redirectAttributes
//     * @return
//     */
//    @RequestMapping(value="/events/{id}", method= RequestMethod.POST, params="action=update")
//    public RedirectView updateEvent(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
//                                    EventFormModel eventFormModel,
//                                    @RequestParam("startRegistrationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startRegistrationDate,
//                                    @RequestParam("endRegistrationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endRegistrationDate,
//                                    @RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime eventDate) {
//        Optional<Category> optionalCategory = categoryService.getCategoryById(eventFormModel.getCategoryId());
//        if(optionalCategory.isPresent()){
//            Category categoryRequest = optionalCategory.get();
//            eventService.updateEvent(id, eventFormModel.getTitle(), eventFormModel.getDescription(), eventFormModel.getCapacity(), startRegistrationDate, endRegistrationDate, eventDate, eventFormModel.getLocation(), categoryRequest);
//        }
//
//        return new RedirectView("/events/{id}");
//    }
//
////    @PostMapping("events")
////    public RedirectView addUser(UserFormModel userFormModel, RedirectAttributes redirectAttributes) {
////
////        userService.addUser(userFormModel.getDisplayName(), userFormModel.getUsername(), userFormModel.getEmail(), userFormModel.getPassword());
////
////        redirectAttributes.addFlashAttribute("success", "User succesfully created!");
////
////        return new RedirectView("/events");
////    }
//
//}
