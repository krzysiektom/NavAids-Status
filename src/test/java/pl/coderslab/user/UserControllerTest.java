package pl.coderslab.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.AuthHandler;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AuthHandler authHandler;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Model model;
    @Mock
    private BindingResult result;

    private String email;
    private String password;

    @InjectMocks
    private UserController tested;

    @Mock
    private User user;

    @Test
    public void shouldAddForm() {
        assertEquals("users/userForm", tested.addForm(model));
    }

    @Test
    public void shouldAddUser() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("users/userForm", tested.addUser(user, result, model));

        Mockito.when(result.hasErrors()).thenReturn(false);
        Mockito.when(userService.isNotExistEmail(user)).thenReturn(true);
        assertEquals("redirect:/tweet/main", tested.addUser(user, result, model));

        Mockito.when(userService.isNotExistEmail(user)).thenReturn(false);
        Mockito.when(authHandler.getUser().getId()).thenReturn(1L);
        assertEquals("users/userForm", tested.addUser(user, result, model));
    }

    @Test
    public void shouldEditForm() {
        Mockito.when(authHandler.getUser()).thenReturn(user);

        assertEquals("users/userForm", tested.editForm(model));
    }

    @Test
    public void shouldEditUser() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("users/userForm", tested.editUser(user, result, model));

        Mockito.when(result.hasErrors()).thenReturn(false);
        Mockito.when(userService.isNotExistAnotherUserWithEmail(user)).thenReturn(false);
        assertEquals("users/userForm", tested.editUser(user, result, model));

        Mockito.when(userService.isNotExistAnotherUserWithEmail(user)).thenReturn(true);
        assertEquals("redirect:/tweet/main", tested.editUser(user, result, model));
    }

    @Test
    public void shouldDeleteUser() {
        Mockito.when(authHandler.getUser()).thenReturn(user);
        assertEquals("redirect:/", tested.deleteUser());
    }

    @Test
    public void shouldLogout() {
        assertEquals("redirect:/", tested.logout());
    }

    @Test
    public void shouldLoginForm() {
        assertEquals("users/loginPage", tested.loginForm());
    }

    @Test
    public void shouldLoginUser() {
        Mockito.when(userService.validateUserAndSetSession(email, password)).thenReturn(true);
        assertEquals("redirect:/tweet/main", tested.loginUser(email, password, model));

        Mockito.when(userService.validateUserAndSetSession(email, password)).thenReturn(false);
        assertEquals("users/loginPage", tested.loginUser(email, password, model));
    }
}