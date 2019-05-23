package pl.coderslab.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    User user;

    @InjectMocks
    UserConverter converter;

    @Test
    public void shouldConvert() {
        Mockito.when(userRepository.findOne(Long.parseLong("1"))).thenReturn(user);
        assertEquals(user, this.converter.convert("1"));
    }
}