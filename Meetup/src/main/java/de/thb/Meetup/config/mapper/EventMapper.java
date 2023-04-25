package de.thb.Meetup.config.mapper;


import de.thb.Meetup.dto.ActionEventDto;
import de.thb.Meetup.dto.EventDto;
import de.thb.Meetup.dto.SlimUserDto;
import de.thb.Meetup.entity.Event;
import de.thb.Meetup.entity.User;

public class EventMapper {
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
