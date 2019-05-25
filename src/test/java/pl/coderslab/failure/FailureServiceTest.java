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

    private Failure failure;
    private Device device;


    @Before
    public void setUp() {
        device = new Device();
        device.setReady(true);
        device.setId(1L);
        failure = new Failure();
        failureAndFix = new FailureAndFix();
        failureAndFix.setDescription("desc");
    }

    @Test
    public void save() {
       /* Long deviceId = 1L;
        Mockito.when(deviceRepository.findOne(deviceId)).thenReturn(device);
        Mockito.when(authHandler.getUser()).thenReturn(user);
        Mockito.when(failureRepository.save(failure)).thenReturn(failure);

        assertEquals(null, tested.save(failureAndFix, deviceId));
        assertFalse(device.isReady());
      //  assertEquals("desc", failure.getDescription());*/
    }
}