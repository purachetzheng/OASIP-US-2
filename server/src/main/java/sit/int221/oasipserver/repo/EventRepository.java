package sit.int221.oasipserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    public List<Event> findAllByOrderByEventStartTimeDesc();
    public List<Event> findAllByEventCategory_IdIs(Integer eventCategory_Id);
    public List<Event> findAllByEventCategoryIs(Eventcategory eventcategory);
}