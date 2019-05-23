package pl.coderslab.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderslab.AuthHandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AuthHandler authHandler;
    @Mock
    private UserRepository userRepository;

    private User user;
    private User userValidate;
    private User userValidate2;

    @InjectMocks
    private UserService tested;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(1L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");
        user.setPassword("password");

        userValidate = new User();
        userValidate.setId(2L);
        userValidate.setEmail("email2");

        userValidate2 = new User();
        userValidate2.setId(3L);
        userValidate2.setEmail("email");


    }

    @Test
    public void shouldValidateUserAndSetSession() {
        Mockito.when(userRepository.findByEmail("email")).thenReturn(user);
        Mockito.when(userRepository.findByEmail("email2")).thenReturn(null);

        assertTrue(tested.validateUserAndSetSession("email", "password"));
        assertFalse(tested.validateUserAndSetSession("email2", "password"));
        assertFalse(tested.validateUserAndSetSession("email", "password2"));
        assertFalse(tested.validateUserAndSetSession("email2", "password2"));
    }


    @Test
    public void shouldIsNotExistEmail() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(userRepository.findByEmail(userValidate.getEmail())).thenReturn(null);

        assertFalse(tested.isNotExistEmail(user));
        assertTrue(tested.isNotExistEmail(userValidate));
    }

    @Test
    public void shouldIsNotExistAnotherUserWithEmail() {
        Mockito.when(authHandler.getUser().getId()).thenReturn(1L);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        assertTrue(tested.isNotExistAnotherUserWithEmail(user));

        Mockito.when(userRepository.findByEmail(userValidate.getEmail())).thenReturn(null);
        assertTrue(tested.isNotExistAnotherUserWithEmail(userValidate));

        Mockito.when(userRepository.findByEmail(userValidate2.getEmail())).thenReturn(userValidate2);
        assertFalse(tested.isNotExistAnotherUserWithEmail(userValidate2));

    }
}