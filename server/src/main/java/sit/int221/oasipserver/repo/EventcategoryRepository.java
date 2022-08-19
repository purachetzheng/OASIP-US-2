package sit.int221.oasipserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;

import java.util.List;

public interface EventcategoryRepository extends JpaRepository<Eventcategory, Integer> {
//    public Eventcategory findByEventCategoryName();
}