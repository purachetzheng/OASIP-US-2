package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Integer id;
    private String bookingName;
    private Integer eventDuration;
    private Instant eventStartTime;
    private Integer eventCategoryId;
    private String eventCategoryName;
}
