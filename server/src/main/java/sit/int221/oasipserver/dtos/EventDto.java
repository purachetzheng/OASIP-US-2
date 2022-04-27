package sit.int221.oasipserver.dtos;

import lombok.Getter;
import lombok.Setter;
import sit.int221.oasipserver.entities.Eventcategory;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
