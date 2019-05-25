package pl.coderslab.owner;

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
public class OwnerControllerTest {
    @InjectMocks
    private OwnerController tested;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private BindingResult result;
    @Mock
    private Model model;
    @Mock
    private Owner owner;

    @Test
    public void allOwners() {
        assertEquals("owners/allOwners", tested.allOwners(model));
    }

    @Test
    public void showForm() {
        assertEquals("owners/ownerForm", tested.showForm(model));
    }

    @Test
    public void addOwner() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("owners/ownerForm", tested.addOwner(owner, result, model));
        Mockito.when(result.hasErrors()).thenReturn(false);
        assertEquals("redirect:/owners/all", tested.addOwner(owner, result, model));


    }
}