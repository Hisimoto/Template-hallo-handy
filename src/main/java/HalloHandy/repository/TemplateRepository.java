package HalloHandy.repository;

import HalloHandy.entity.Template;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {
    @Query(value = "SELECT * FROM template", nativeQuery = true)
    List<Template> findAll();
}
