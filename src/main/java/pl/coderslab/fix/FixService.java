package pl.coderslab.fix;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.AuthHandler;
import pl.coderslab.device.Device;
import pl.coderslab.device.DeviceRepository;
import pl.coderslab.failure.Failure;
import pl.coderslab.failure.FailureRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class FixService {
    private final FixRepository fixRepository;
    private final FailureRepository failureRepository;
    private final AuthHandler authHandler;
    private final DeviceRepository deviceRepository;

    public FixService(FixRepository fixRepository, FailureRepository failureRepository, AuthHandler authHandler, DeviceRepository deviceRepository) {
        this.fixRepository = fixRepository;
        this.failureRepository = failureRepository;
        this.authHandler = authHandler;
        this.deviceRepository = deviceRepository;
    }

    public void save(Fix fix, Long failureId, boolean isRepaired) {
        Failure failure = failureRepository.findOne(failureId);
        fix.setFailure(failure);
        fix.setUser(authHandler.getUser());
        fixRepository.save(fix);
        if (isRepaired) {
            failure.setFixed(true);
            failure.setFinished(LocalDateTime.now());
            failureRepository.save(failure);
            Device device = failure.getDevice();
            device.setReady(true);
            deviceRepository.save(device);
        }
    }
}
