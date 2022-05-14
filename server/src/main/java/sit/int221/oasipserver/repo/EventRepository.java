package sit.int221.oasipserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    public List<Event> findAllByOrderByEventStartTimeDesc();
    public List<Event> findAllByEventCategoryIs(Eventcategory eventcategory);
    public List<Event> findAllByEventCategoryIsAndIdIsNot (Eventcategory eventcategory, Integer id);
}