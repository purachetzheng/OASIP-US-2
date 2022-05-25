package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventcategoryDto {
    private String id;
    @NotNull
    @Pattern(regexp = "^(?=\\S).*[^.\\s]$", message = "should be trimmed")
    @Size(max = 100, min = 1, message = "size must be between 1 and 500")
    private String eventCategoryName;
    @Pattern(regexp = "^(?=\\S).*[^.\\s]$", message = "should be trimmed")
    @Size(max = 500, message = "size must be between 1 and 100")
    private String eventCategoryDescription;
    @NotNull
    @Range(min = 1, max = 480, message = "duration must be between 1 and 480")
    private Integer eventDuration;

}
