package de.thb.Meetup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.thb.Meetup.entity.Category;
import de.thb.Meetup.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Data
@Builder
public class EventDto {

    private Long id;

    private String title;

    private int capacity;

//    private float percentageFilled;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startRegistrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endRegistrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventDate;

    private String location;

    private Category category;

    private User creator;

    private Collection<User> participants;

}
