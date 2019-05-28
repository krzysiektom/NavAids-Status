package pl.coderslab.failure;

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
import pl.coderslab.fix.FixRepository;
import pl.coderslab.user.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class FailureServiceTest {
    @InjectMocks
    private FailureService tested;
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private AuthHandler authHandler;
    @Mock
    private FailureRepository failureRepository;
    @Mock
    private FixRepository fixRepository;
    @Mock
    private FailureAndFix failureAndFix;
    @Mock
    private User user;


    @Before
    public void setUp() {
    }

    @Test
    public void save() {
   /*     Failure failure = new Failure();
        Failure failureAfterSave = new Failure();
        failureAfterSave.setId(1L);
        failureAndFix = new FailureAndFix();
        failureAndFix.setDescription("desc");
        Long deviceId = 1L;

        Device device = new Device();
        device.setReady(true);
        device.setId(1L);

        Mockito.when(deviceRepository.findOne(deviceId)).thenReturn(device);
        Mockito.when(authHandler.getUser()).thenReturn(user);
        Mockito.when(failureRepository.save(failure)).thenReturn(failureAfterSave);

        assertEquals(null, tested.save(failureAndFix, deviceId));
        assertFalse(device.isReady());
        assertEquals("desc", failure.getDescription());*/
    }
}