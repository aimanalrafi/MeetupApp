package de.thb.Meetup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.thb.Meetup.entity.Event;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Builder
public class CategoryDto {

    private Long id;

    private String name;

}
