package sit.int221.oasipserver.dtos;

import javax.validation.constraints.*;
import java.time.Instant;

public class CreateEventDto extends UpdateEventDto{

    @Size(max = 100, message = "size must be between 1 and 100")
    @NotBlank(message = "must not be blank")
//    @NotNull(message = "bookingName must not be null")
    private String bookingName;

    @Size(max = 100, message = "size must be between 1 and 100")
    @Email(message = "must be a well-formed email address")
    @NotBlank(message = "must not be blank")
//    @NotNull(message = "bookingEmail must not be null")
    private String bookingEmail;

    private Integer eventDuration;

    @NotNull(message = "must not be null")
    private Integer eventCategoryId;
    private String eventCategoryName;
}
