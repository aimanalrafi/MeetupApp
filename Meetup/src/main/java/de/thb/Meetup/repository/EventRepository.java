package de.thb.Meetup.repository;

import de.thb.Meetup.entity.Category;
import de.thb.Meetup.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Queue;

@RepositoryDefinition(domainClass = Event.class, idClass = Long.class)
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("select e from event e where e.category.id = :categoryId")
    List<Event> findAllByCategory(@Param("categoryId")Long categoryId);

    @Query("select e from event e where e.creator.id = :creatorId")
    List<Event> findAllByCreator(@Param("creatorId")Long creatorId);


}
