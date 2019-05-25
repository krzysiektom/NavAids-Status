package pl.coderslab.group;

import org.junit.Before;
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
public class GroupControllerTest {
    @InjectMocks
    private GroupController tested;

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private BindingResult result;

    @Mock
    private Model model;
    @Mock
    private Group group;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldAddForm() {
        assertEquals("groups/groupForm", tested.addForm(model));
    }

    @Test
    public void shouldAddGroup() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("groups/groupForm", tested.addGroup(group, result));

        Mockito.when(result.hasErrors()).thenReturn(false);
        assertEquals("redirect:/groups/all", tested.addGroup(group, result));
    }

    @Test
    public void shouldAllGroups() {
        assertEquals("groups/allGroups", tested.allGroups(model));
    }
}