package pl.coderslab.type;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.group.Group;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByGroup(Group group);
}
