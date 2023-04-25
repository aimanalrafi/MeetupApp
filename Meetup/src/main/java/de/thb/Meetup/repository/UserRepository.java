package de.thb.Meetup.repository;

import de.thb.Meetup.entity.Category;
import de.thb.Meetup.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from user u where u.username in :username")
    Optional<User> findUserByUsername(@Param("username") String username);

}
