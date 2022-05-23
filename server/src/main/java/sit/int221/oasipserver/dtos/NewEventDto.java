package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEventDto  {

    private Integer id;

    @Size(max = 100, message = "size must be between 1 and 100")
    @NotBlank(message = "must not be blank")
    private String bookingName;

    @Size(max = 100, message = "size must be between 1 and 100")
    @Email(message = "must be a well-formed email address")
    @NotBlank(message = "must not be blank")
    private String bookingEmail;
    private Integer eventDuration;

    @NotNull(message = "must not be null")
    @Future(message = "must be a future date")
    private Instant eventStartTime;
    @Size(max = 500, message = "size must be between 0 and 500")

    @Size(min = 0, max = 500, message = "size must be between 0 and 500")
    private String eventNotes;
    @NotNull(message = "must not be null")
    private Integer eventCategoryId;
    private String eventCategoryName;

}
