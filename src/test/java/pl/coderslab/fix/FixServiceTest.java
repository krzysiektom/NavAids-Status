package pl.coderslab.fix;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderslab.AuthHandler;
import pl.coderslab.device.Device;
import pl.coderslab.device.DeviceRepository;
import pl.coderslab.failure.Failure;
import pl.coderslab.failure.FailureRepository;
import pl.coderslab.user.User;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FixServiceTest {

    @InjectMocks
    private FixService tested;
    @Mock
    private FailureRepository failureRepository;
    @Mock
    private FixRepository fixRepository;
    @Mock
    private AuthHandler authHandler;
    @Mock
    private DeviceRepository deviceRepository;

    private Device device;
    private User user;
    private Fix fix;
    private Failure failure;

    private Long failureId;

    @Before
    public void setUp() {
        device = new Device();
        failure = new Failure();
        failure.setId(1L);
        failure.setDevice(device);
        fix = new Fix();
    }

    @Test
    public void save() {
        Mockito.when(failureRepository.findOne(failureId)).thenReturn(failure);
        tested.save(fix, failureId, false);
        assertEquals(fix.getFailure(), failure);
        assertEquals(fix.getUser(), user);

        tested.save(fix, failureId, true);
        assertTrue(failure.isFixed());
        assertTrue(LocalDateTime.now().isAfter(failure.getFinished()));
        assertTrue(device.isReady());
    }
}