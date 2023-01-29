package HalloHandy.repository;

import HalloHandy.entity.Template;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

}
