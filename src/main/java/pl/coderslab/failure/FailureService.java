package pl.coderslab.failure;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    AuthHandler authHandler;
    @Autowired
    FailureRepository failureRepository;
    @Autowired
    FixRepository fixRepository;

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
        fix.setUsedMaterials(failureAndFix.getDescription());
        fixRepository.save(fix);
        return failure.getId();
    }
}
