package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Integer eventDuration;
    private Instant eventStartTime;
    private String eventNotes;
    private Integer eventCategoryId;
    private String eventCategoryName;
//    private String eventCategoryEventCategoryDescription;
//    private List<Event> eventCategoryEvents;
//    private Eventcategory eventCategory;
}
