package sit.int221.oasipserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipserver.entities.Eventcategory;

public interface EventcategoryRepository extends JpaRepository<Eventcategory, String> {
}