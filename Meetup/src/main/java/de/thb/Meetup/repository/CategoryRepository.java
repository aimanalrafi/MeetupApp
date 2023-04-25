package de.thb.Meetup.repository;

import de.thb.Meetup.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass = Category.class, idClass = Long.class)
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
