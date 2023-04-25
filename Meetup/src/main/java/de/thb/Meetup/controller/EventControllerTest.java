package de.thb.Meetup.controller;

import de.thb.Meetup.dto.ActionEventDto;
import de.thb.Meetup.dto.EventDto;
import de.thb.Meetup.dto.SlimUserDto;
import de.thb.Meetup.entity.Event;
import de.thb.Meetup.entity.User;
import de.thb.Meetup.service.EventService;
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
@RequestMapping("/event")
public class EventControllerTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;



    @GetMapping("/all")
    public List<EntityModel<EventDto>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();

        List<EventDto> eventDtoList = events
                .stream()
                .map(EventControllerTest::mapToEventDto
                )
                .collect(Collectors.toList());

        List<EntityModel<EventDto>> entityModels = new ArrayList<>();

//        for(EventDto eventDto: eventDtoList) {
//            entityModels.add(EntityModel.of(eventDto,
//                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventControllerTest.class).getById(eventDto.getId())).withSelfRel(),
//                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventControllerTest.class).getCreator(eventDto.getId())).withRel("creator")
//        ));
//        }

        for(EventDto eventDto: eventDtoList) {
            entityModels.add(EntityModel.of(eventDto));
        }

        return entityModels;
    }

    @GetMapping("/all-events-by-category/{categoryId}")
    public List<EntityModel<EventDto>> getEventsByCategory(@PathVariable("categoryId") Long categoryId) {
        List<Event> events = eventService.getEventsByCategory(categoryId);

        List<EventDto> eventDtoList = events
                .stream()
                .map(EventControllerTest::mapToEventDto
                )
                .collect(Collectors.toList());

        List<EntityModel<EventDto>> entityModels = new ArrayList<>();

        for(EventDto eventDto: eventDtoList) {
            entityModels.add(EntityModel.of(eventDto));
        }

        return entityModels;
    }

    @GetMapping("/all-events-by-creator/{creatorId}")
    public List<EntityModel<EventDto>> getEventsByCreator(@PathVariable("creatorId") Long creatorId) {
        List<Event> events = eventService.getEventsByCreator(creatorId);

        List<EventDto> eventDtoList = events
                .stream()
                .map(EventControllerTest::mapToEventDto
                )
                .collect(Collectors.toList());

        List<EntityModel<EventDto>> entityModels = new ArrayList<>();

        for(EventDto eventDto: eventDtoList) {
            entityModels.add(EntityModel.of(eventDto));
        }

        return entityModels;
    }

    @GetMapping("/joined-events/{creatorId}")
    public List<EntityModel<EventDto>> getJoinedEvents(@PathVariable("creatorId") Long creatorId) {
        List<Event> events = eventService.getJoinedEvents(creatorId);

        List<EventDto> eventDtoList = events
                .stream()
                .map(EventControllerTest::mapToEventDto
                )
                .collect(Collectors.toList());

        List<EntityModel<EventDto>> entityModels = new ArrayList<>();

        for(EventDto eventDto: eventDtoList) {
            entityModels.add(EntityModel.of(eventDto));
        }

        return entityModels;
    }

    @GetMapping("/{id}")
    public EntityModel<EventDto> getById(@PathVariable("id") Long id) {
        Optional<Event> eventOpt = eventService.getEventById(id);

        if (eventService.getEventById(id).isPresent()) {
            EventDto eventDto = eventOpt.map(EventControllerTest::mapToEventDto
            ).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event not found")
            );
            return EntityModel.of(eventDto);


        } else {
            //könnte man noch einkürzen mit orElseThrow
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event not found");
        }
    }

    @GetMapping("/{id}/creator")
    public EntityModel<SlimUserDto> getCreator(@PathVariable("id") Long eventId) {

        Event event = eventService.getEventById(eventId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "event not found")
        );


        if (userService.getUserById(event.getCreator().getId()).isPresent()) {
            Optional<User> creatorOpt = userService.getUserById(event.getCreator().getId());
            SlimUserDto slimUserDto = creatorOpt.map(EventControllerTest::mapToUserDto).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
            );

            return EntityModel.of(slimUserDto);
//            return EntityModel.of(slimUserDto,
//                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventControllerTest.class).getCreator(eventId)).withSelfRel());


        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody ActionEventDto actionEventDto) {
        Event createdEvent = eventService.addEvent(actionEventDto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
//
    @PutMapping("/{id}/update")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") Long eventId, @RequestBody ActionEventDto actionEventDto) {
        Event updatedEvent = eventService.updateEvent(eventId, actionEventDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }
//
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public static EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .capacity(event.getCapacity())
                .startRegistrationDate(event.getStartRegistrationDate())
                .endRegistrationDate(event.getEndRegistrationDate())
                .eventDate(event.getEventDate())
                .location(event.getLocation())
                .category(event.getCategory())
                .creator(event.getCreator())
                .participants(event.getParticipants())
                .build();
    }

    public static SlimUserDto mapToUserDto(User user) {
        return SlimUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

}
