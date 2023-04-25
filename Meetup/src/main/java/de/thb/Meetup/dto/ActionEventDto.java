package de.thb.Meetup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.thb.Meetup.entity.Category;
import de.thb.Meetup.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Data
@Builder
public class ActionEventDto {

    private Long id;

    private String title;

    private int capacity;

//    private float percentageFilled;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startRegistrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endRegistrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime eventDate;

    private String location;

    //incoming json cant be deserialized into attribute with Long
    //will be converted into type category in Service
    private int categoryId;

    private int creatorId;

    private Collection<User> participants = new HashSet<>();

//    private Category category;
//
//    private User creator;

}
