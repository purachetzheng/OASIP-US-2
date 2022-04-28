package sit.int221.oasipserver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
@Setter @Getter
@Entity
@Table(name = "eventcategories")
public class Eventcategory {
    @Id
    @Column(name = "eventCategoryName", nullable = false, length = 100)
    private String id;

    @Column(name = "eventCategoryDescription", length = 500)
    private String eventCategoryDescription;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @OneToMany(mappedBy = "eventCategory")
    private Set<Event> events = new LinkedHashSet<>();


}