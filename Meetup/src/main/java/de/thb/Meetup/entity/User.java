package de.thb.Meetup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "display_name")
//    private String displayName;

    private String username;

    private String password;

    private String email;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
//    private List<Event> createdEvents;


//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "participants")
//    @JsonIgnore
//    private Set<Event> events = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "participants") //non-owning side
    private Set<Event> joinedEvents = new HashSet<>();

}
