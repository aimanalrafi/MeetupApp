package de.thb.Meetup.dto;


import de.thb.Meetup.entity.Event;
import lombok.*;

import java.util.Collection;

@Data
@Builder
public class UserNoPasswordDto {

    private Long id;

    private String email;

    private String username;

    private Collection<Event> joinedEvents;
}

