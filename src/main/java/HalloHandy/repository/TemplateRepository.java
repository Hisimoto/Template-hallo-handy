package HalloHandy.repository;

import HalloHandy.entity.Template;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemplateRepository extends CrudRepository<Template, Long>, QuerydslPredicateExecutor<Template> {
    @Query(value = "SELECT * FROM template", nativeQuery = true)
    List<Template> findAllForExport();

    Template getTemplateById(Long id);
}
