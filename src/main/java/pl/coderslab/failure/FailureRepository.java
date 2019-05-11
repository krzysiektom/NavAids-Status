package pl.coderslab.failure;

import org.springframework.data.jpa.repository.JpaRepository;

interface FailureRepository extends JpaRepository<Failure, Long> {
}
