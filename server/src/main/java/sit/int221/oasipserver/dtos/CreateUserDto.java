package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private Integer id;

    @NotBlank(message = "must not be blank")
    private String name;

    @Size(max = 50, message = "size must be between 1 and 50")
    @Email(message = "must be a well-formed email address")
    @NotBlank(message = "must not be blank")
    private String email;

    private Role role;

    private Instant createdOn;

    private Instant updatedOn;
}

