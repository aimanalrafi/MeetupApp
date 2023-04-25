package de.thb.Meetup.dto;

import de.thb.Meetup.entity.Event;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ActionUserDto {
    private Long id;

//    private String displayName;

    private String email;

    private String username;

    private String password;

}
