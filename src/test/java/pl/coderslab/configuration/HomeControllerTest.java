package pl.coderslab.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import pl.coderslab.AuthHandler;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.owner.OwnerService;
import pl.coderslab.user.UserRepository;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @InjectMocks
    private HomeController tested;
    @Mock
    AirfieldRepository airfieldRepository;
    @Mock
    OwnerService ownerService;
    @Mock
    UserRepository userRepository;
    @Mock
    AuthHandler authHandler;

    @Mock
    Model model;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldHomePage() {
        assertEquals("index", tested.homePage(model));
    }
}