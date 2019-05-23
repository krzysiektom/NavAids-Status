package pl.coderslab;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderslab.user.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuthHandlerTest {

    @Mock
    User user;

    boolean isLogged;

    @InjectMocks
    private AuthHandler authHandler;

    @Before
    public void setUp() throws Exception {
        authHandler = new AuthHandler(user, isLogged);
    }

    @Test
    public void getUser() {
        assertEquals(isLogged, authHandler.isLogged());
    }

    @Test
    public void setUser() {
        authHandler.setUser(new User());
        assertNotEquals(user,authHandler.getUser());
    }

    @Test
    public void isLogged() {
        assertEquals(false, authHandler.isLogged());
    }

    @Test
    public void setLogged() {
        authHandler.setLogged(true);
        assertEquals(true,authHandler.isLogged());
    }
}