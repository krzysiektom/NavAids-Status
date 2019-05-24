package pl.coderslab.fix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FixControllerTest {
    @InjectMocks
    private FixController tested;

    @Mock
    private FixService fixService;
    @Mock
    private FixRepository fixRepository;
    @Mock
    private BindingResult result;
    @Mock
    private Model model;
    @Mock
    private Fix fix;

    private Long id;
    private Long deviceId;
    boolean isRepaired;

    @Test
    public void fixById() {
        Mockito.when(fixRepository.findOne(id)).thenReturn(fix);
        assertEquals("fixes/fixPage", tested.fixById(id, model));
    }

    @Test
    public void showForm() {
        assertEquals("fixes/fixForm", tested.showForm(deviceId, model));
    }

    @Test
    public void addFix() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("fixes/fixForm", tested.addFix(deviceId, fix, result, isRepaired));

        Mockito.when(result.hasErrors()).thenReturn(false);
        assertEquals("redirect:/failures/1", tested.addFix(1L, fix, result, false));

        assertEquals("redirect:/devices/1", tested.addFix(1L, fix, result, true));
    }
}