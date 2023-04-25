package de.thb.Meetup.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "event")
public class Event implements Serializable {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int capacity;

    private String description;

//    private float percentageFilled;
//
//    public float getPercentageFilled() {
//        return percentageFilled;
//    }
//
//    public void setPercentageFilled() {
//        float percentageFilled = ((float) this.participants.size() / (float) this.getCapacity()) * 100.0f;
//        this.percentageFilled = percentageFilled;
//    }

    /**
     * @ TODO: 31.10.22
     * @DateTimeFormat
     */
    @Column(name = "start_registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startRegistrationDate;

    @Column(name = "end_registration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endRegistrationDate;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventDate;

    private String location;

    @ManyToOne
    @JoinColumn(name="f_category_id", referencedColumnName = "category_id")
    private Category category;
//
    @ManyToOne(optional = false)
    @JoinColumn(name = "f_creator_id", referencedColumnName = "user_id")
    private User creator;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "event_list",
//            joinColumns = @JoinColumn(name = "f_event_id"),
//            inverseJoinColumns = @JoinColumn(name = "f_user_id"))
//    @ToString.Exclude
//    private Set<User> participants;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "event_list",
        joinColumns = @JoinColumn(name = "f_event_id"),
        inverseJoinColumns = @JoinColumn(name = "f_user_id"))
    private Collection<User> participants = new HashSet<User>();

    public Collection<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
    }
}
