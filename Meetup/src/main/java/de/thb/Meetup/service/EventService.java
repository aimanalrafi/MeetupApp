package de.thb.Meetup.service;

import de.thb.Meetup.dto.ActionEventDto;
import de.thb.Meetup.entity.Category;
import de.thb.Meetup.entity.Event;
import de.thb.Meetup.entity.User;
import de.thb.Meetup.repository.CategoryRepository;
import de.thb.Meetup.repository.EventRepository;
import de.thb.Meetup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByCategory(Long categoryId) {
        return eventRepository.findAllByCategory(categoryId);
    }

    public List<Event> getEventsByCreator(Long creatorId) {
        return eventRepository.findAllByCreator(creatorId);
    }

    public List<Event> getJoinedEvents(Long creatorId) {
        List<Event> joinedEvents = new ArrayList<>();
        for (Event event: getAllEvents()){
            if(event.getCreator().getId() != creatorId){
                for(User user: event.getParticipants()){
                    if(user.getId() == creatorId){
                        joinedEvents.add(event);
                    }
                }
            }
        }
        return joinedEvents;
    }

    public Event addEvent(ActionEventDto actionEventDto) {
//        Optional<Category> optCategory = categoryRepository.findById((long) actionEventDto.getCategory().getId());
//        Optional<User> optCreator = userRepository.findById((long) actionEventDto.getCreator().getId());
        Optional<Category> optCategory = categoryRepository.findById((long) actionEventDto.getCategoryId());
        Optional<User> optCreator = userRepository.findById((long) actionEventDto.getCreatorId());

        if(optCategory.isPresent() && optCreator.isPresent()){
            Collection<User> initParticipants = new ArrayList<>();
            initParticipants.add(optCreator.get());
            return eventRepository.save(Event.builder()
                    .title(actionEventDto.getTitle())
                    .description(actionEventDto.getDescription())
                    .capacity(actionEventDto.getCapacity())
                    .startRegistrationDate(actionEventDto.getStartRegistrationDate())
                    .endRegistrationDate(actionEventDto.getEndRegistrationDate())
                    .eventDate(actionEventDto.getEventDate())
                    .location(actionEventDto.getLocation())
                    .category(optCategory.get())
                    .creator(optCreator.get())
                    .participants(initParticipants)
                    .build());
        } else
            return null;
    }


    public void deleteEventById(Long id){
        Optional<Event> eventOptional = getEventById(id);
        eventRepository.delete(eventOptional.get());
    }




    public Event updateEvent(Long eventId, ActionEventDto actionEventDto) {
//        Optional<Category> optCategory = categoryRepository.findById((long) actionEventDto.getCategory().getId());
//        Optional<User> optCreator = userRepository.findById((long) actionEventDto.getCreator().getId());

        Optional<Category> optCategory = categoryRepository.findById((long) actionEventDto.getCategoryId());
        Optional<User> optCreator = userRepository.findById((long) actionEventDto.getCreatorId());
        if (eventRepository.findById(eventId).isPresent()) {
            return eventRepository.save(Event.builder()
                    .id(eventId)
                    .title(actionEventDto.getTitle())
                    .description(actionEventDto.getDescription())
                    .capacity(actionEventDto.getCapacity())
//                    .percentageFilled(actionEventDto.getPercentageFilled())
                    .startRegistrationDate(actionEventDto.getStartRegistrationDate())
                    .endRegistrationDate(actionEventDto.getEndRegistrationDate())
                    .eventDate(actionEventDto.getEventDate())
                    .location(actionEventDto.getLocation())
                    .category(optCategory.get())
                    .creator(optCreator.get())
                    .participants(eventRepository.findById(eventId).get().getParticipants())
                    .build());
        } else
            return null;
    }

    public float calculatePercentageFilled(Event event) {
        return ((float) event.getParticipants().size() / (float) event.getCapacity()) * 100.0f;
    }
}
