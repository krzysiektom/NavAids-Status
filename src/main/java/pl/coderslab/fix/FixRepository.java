package pl.coderslab.fix;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.failure.Failure;

import java.util.List;

public interface FixRepository extends JpaRepository<Fix, Long> {
    List<Fix> findAllByFailure(Failure failure);
}
