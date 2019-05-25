package pl.coderslab.failure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.AuthHandler;
import pl.coderslab.device.Device;
import pl.coderslab.device.DeviceRepository;
import pl.coderslab.fix.Fix;
import pl.coderslab.fix.FixRepository;

@Service
@Transactional
public class FailureService {
    private final DeviceRepository deviceRepository;
    private final AuthHandler authHandler;
    private final FailureRepository failureRepository;
    private final FixRepository fixRepository;

    public FailureService(DeviceRepository deviceRepository, AuthHandler authHandler, FailureRepository failureRepository, FixRepository fixRepository) {
        this.deviceRepository = deviceRepository;
        this.authHandler = authHandler;
        this.failureRepository = failureRepository;
        this.fixRepository = fixRepository;
    }

    public Long save(FailureAndFix failureAndFix, Long deviceId) {
        Device device = deviceRepository.findOne(deviceId);
        device.setReady(false);
        deviceRepository.save(device);
        Failure failure = new Failure();
        failure.setDescription(failureAndFix.getDescription());
        failure.setDevice(device);
        failure.setUser(authHandler.getUser());
        failureRepository.save(failure);
        Fix fix = new Fix();
        fix.setUser(authHandler.getUser());
        fix.setFailure(failure);
        fix.setDone(failureAndFix.getDone());
        fix.setUsedMaterials(failureAndFix.getUsedMaterials());
        fixRepository.save(fix);
        return failure.getId();
    }
}
