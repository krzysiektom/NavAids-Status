package pl.coderslab.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderslab.AuthHandler;
import pl.coderslab.device.DeviceRepository;
import pl.coderslab.failure.FailureRepository;
import pl.coderslab.fix.Fix;
import pl.coderslab.fix.FixRepository;
import pl.coderslab.fix.FixService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {
    @InjectMocks
    private FixService fixService;
    @Mock
    private  FailureRepository failureRepository;
    @Mock
    private FixRepository fixRepository;
    @Mock
    private AuthHandler authHandler;
    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    Fix fix;
    Long failureId;
    boolean isRepaired;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findAllOwners() {
    }

    @Test
    public void findAllBySuperior() {
    }
}