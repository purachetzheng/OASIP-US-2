package sit.int221.oasipserver.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import sit.int221.oasipserver.entities.Event;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class EventcategoryDto {
    private String id;
    private String eventCategoryDescription;
    private Integer eventDuration;
//    private Set<Event> events = new LinkedHashSet<>();

}
