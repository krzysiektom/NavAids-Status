package pl.coderslab.failure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.device.Device;
import pl.coderslab.device.DeviceRepository;
import pl.coderslab.fix.FixRepository;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FailureControllerTest {
    @InjectMocks
    private FailureController tested;
    @Mock
    private FailureRepository failureRepository;
    @Mock
    private FixRepository fixRepository;
    @Mock
    private FailureService failureService;
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private Model model;
    @Mock
    private  FailureAndFix failureAndFix;
    @Mock
    private BindingResult result;
    @Mock
    private Device device;
    @Mock
    private Failure failure;

    private Long id;
    private Long deviceId;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldAllFailures() {
        assertEquals("failures/allFailures", tested.allFailures(model));

    }

    @Test
    public void shouldFailureById() {
        assertEquals("failures/failurePage", tested.failureById(id, model));
    }

    @Test
    public void shouldAddForm() {
        assertEquals("failures/failureForm", tested.addForm(model));
    }

    @Test
    public void shouldAddFailure() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("failures/failureForm", tested.addFailure(deviceId, failureAndFix, result));
        Mockito.when(result.hasErrors()).thenReturn(false);
        Mockito.when(failureService.save(failureAndFix, deviceId)).thenReturn(1L);
        assertEquals("redirect:/failures/1", tested.addFailure(deviceId, failureAndFix, result));
    }
    @Test
    public void failureByDeviceId(){
        Mockito.when(deviceRepository.findOne(deviceId)).thenReturn(device);
        Mockito.when(failureRepository.findByDeviceAndIsFixedIsFalse(device)).thenReturn(failure);
        assertEquals("redirect:/failures/0",tested.failureByDeviceId(deviceId,model));
    }
}