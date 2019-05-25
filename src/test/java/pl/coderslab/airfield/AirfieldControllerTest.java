package pl.coderslab.airfield;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.owner.OwnerRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AirfieldControllerTest {
    @InjectMocks
    private AirfieldController tested;
    @Mock
    private AirfieldRepository airfieldRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private BindingResult result;
    @Mock
    private Model model;
    @Mock
    private Airfield airfield;


    @Test
    public void allAirfields() {
        assertEquals("airfields/allAirfields", tested.allAirfields(model));
    }

    @Test
    public void showForm() {
        assertEquals("airfields/airfieldForm", tested.showForm(model));
    }

    @Test
    public void addAirfield() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("airfields/airfieldForm",tested.addAirfield(airfield,result,model));
        Mockito.when(result.hasErrors()).thenReturn(false);
        assertEquals("redirect:/airfields/all",tested.addAirfield(airfield,result,model));
    }
}