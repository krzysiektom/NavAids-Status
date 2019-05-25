package pl.coderslab.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.group.GroupRepository;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TypeControllerTest {
    @InjectMocks
    private TypeController tested;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private BindingResult result;
    @Mock
    private Model model;
    @Mock
    private Type type;

    private Long groupId;


    @Test
    public void allTypes() {
        assertEquals("types/allTypes",tested.allTypes(model));
    }

    @Test
    public void showForm() {
        assertEquals("types/typeForm",tested.showForm(model));
    }

    @Test
    public void addType() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("types/typeForm",tested.addType(type,result,model));

        Mockito.when(result.hasErrors()).thenReturn(false);
        assertEquals("redirect:/types/all",tested.addType(type,result,model));
    }

    @Test
    public void typesInGroup() {
        assertEquals("types/typesByGroup",tested.typesInGroup(groupId,model));
    }
}