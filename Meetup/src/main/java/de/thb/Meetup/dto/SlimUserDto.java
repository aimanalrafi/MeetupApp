package de.thb.Meetup.dto;

import de.thb.Meetup.entity.Event;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SlimUserDto {
    private Long id;

    private String username;

}
