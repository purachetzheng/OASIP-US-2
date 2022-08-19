package sit.int221.oasipserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;

import java.time.Instant;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    public List<Event> findAllByOrderByEventStartTimeDesc();
    public List<Event> findAllByEventCategoryIs(Eventcategory eventcategory);
//    public List<Event> findAllByEventCategoryIsAndEventStartTimeBetween(Eventcategory eventcategory, Instant lower, Integer upper);
    public List<Event> findAllByEventCategoryIsAndEventStartTimeBetween(Eventcategory eventcategory, Instant lower, Instant upper);
    public List<Event> findAllByEventCategoryId(Integer eventCategoryId);
    public List<Event> findAllByEventCategoryIsAndIdIsNot (Eventcategory eventcategory, Integer id);
    public List<Event> findAllByEventCategoryIsAndIdIsNotAndEventStartTimeBetween (Eventcategory eventcategory, Integer id, Instant lower, Instant upper);
}