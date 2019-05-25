package pl.coderslab.failure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.device.Device;

import java.util.List;

public interface FailureRepository extends JpaRepository<Failure, Long> {
    List<Failure> findAllByIsFixedIsFalse();
    Failure findByDeviceAndIsFixedIsFalse(Device device);

}
