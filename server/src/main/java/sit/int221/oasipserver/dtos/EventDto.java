package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

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
}
