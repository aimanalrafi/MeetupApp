package de.thb.Meetup.dto;

import de.thb.Meetup.entity.Event;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class UserDto {
    private Long id;

    private String email;

    private String username;

    private String password;

    private Collection<Event> joinedEvents;
}
