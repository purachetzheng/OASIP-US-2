package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sit.int221.oasipserver.entities.Event;
import sit.int221.oasipserver.entities.Eventcategory;

import java.time.Instant;
import java.util.List;

@Getter
@Setter

public class EventDto {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private String eventCategoryId;
    private Integer eventDuration;
    private Instant eventStartTime;
    private String eventNotes;
//    private String eventCategoryEventCategoryDescription;
//    private List<Event> eventCategoryEvents;
//    private Eventcategory eventCategory;
}
