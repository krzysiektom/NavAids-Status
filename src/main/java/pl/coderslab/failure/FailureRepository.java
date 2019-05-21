package pl.coderslab.failure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FailureRepository extends JpaRepository<Failure, Long> {
    List<Failure> findAllByIsFixedIsFalse();

}
