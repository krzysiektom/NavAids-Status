package pl.coderslab.owner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {
    @InjectMocks
    private OwnerService tested;
    @Mock
    private OwnerRepository ownerRepository;

    private List<Owner> allOwners;
    private List<OwnerBySuperior> allBySuperior;

    @Before
    public void setUp() {

        Owner superior = new Owner(1L, null);

        Owner owner1 = new Owner(2L, superior);
        Owner owner2 = new Owner(3L, superior);

        List<Owner> superiors = new ArrayList<>();
        superiors.add(owner1);
        superiors.add(owner2);

        Owner owner11 = new Owner(4L, owner1);
        Owner owner12 = new Owner(6L, owner1);
        Owner owner21 = new Owner(5L, owner2);
        Owner owner22 = new Owner(7L, owner2);

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner11);
        ownerList.add(owner21);
        ownerList.add(owner12);
        ownerList.add(owner22);
        ownerList.addAll(superiors);
        ownerList.add(superior);

        allOwners = new ArrayList<>();
        allOwners.add(owner11);
        allOwners.add(owner12);
        allOwners.add(owner21);
        allOwners.add(owner22);

        allBySuperior = new ArrayList<>();
        allBySuperior.add(new OwnerBySuperior(owner1, new ArrayList<Owner>(Arrays.asList(owner11, owner12))));
        allBySuperior.add(new OwnerBySuperior(owner2, new ArrayList<Owner>(Arrays.asList(owner21, owner22))));

        Mockito.when(ownerRepository.findOne(1L)).thenReturn(superior);
        Mockito.when(ownerRepository.findAllBySuperior(superior)).thenReturn(superiors);
        Mockito.when(ownerRepository.findAll()).thenReturn(ownerList);
        Mockito.when(ownerRepository.findOne(1L)).thenReturn(superior);
    }

    @Test
    public void shouldFindAllOwners() {
        assertEquals(allOwners, tested.findAllOwners());
    }

    @Test
    public void shouldFindAllBySuperior() {
        assertEquals(allBySuperior, tested.findAllBySuperior());
    }
}