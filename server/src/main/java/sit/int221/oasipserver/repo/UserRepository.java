package sit.int221.oasipserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipserver.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findAllByOrderByNameAsc();
}
