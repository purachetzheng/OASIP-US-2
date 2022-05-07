package sit.int221.oasipserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventcategoryDto {
    private String id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventDuration;
//    private Set<Event> events = new LinkedHashSet<>();

}
