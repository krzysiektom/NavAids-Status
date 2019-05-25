package pl.coderslab.device;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.airfield.AirfieldRepository;
import pl.coderslab.group.Group;
import pl.coderslab.group.GroupRepository;
import pl.coderslab.owner.OwnerRepository;
import pl.coderslab.type.Type;
import pl.coderslab.type.TypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest {
    @InjectMocks
    private DeviceController tested;
    @Mock
    private DeviceService deviceService;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private AirfieldRepository airfieldRepository;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    BindingResult result;
    @Mock
    private Model model;
    @Mock
    Device device;
    @Mock
    private Type type;

    Long id;

    @Before
    public void setUp() {
        device = new Device();
        device.setId(1L);
    }

    @Test
    public void allDevices() {
        assertEquals("devices/allDevices", tested.allDevices(model));
    }

    @Test
    public void showForm() {
        assertEquals("devices/deviceForm", tested.showForm(model));
    }

    @Test
    public void addDevice() {
        Mockito.when(result.hasErrors()).thenReturn(true);
        assertEquals("devices/deviceForm", tested.addDevice(device, result, model));

        Mockito.when(result.hasErrors()).thenReturn(false);

        assertEquals("redirect:/devices/1", tested.addDevice(device, result, model));
        assertTrue(device.isReady());
    }

    @Test
    public void deviceDetails() {
        assertEquals("devices/devicePage", tested.deviceDetails(id, model));
    }

    @Test
    public void devicesByType() {
        assertEquals("devices/devicesByType", tested.devicesByType(id, model));
    }

    @Test
    public void devicesByOwner() {
        assertEquals("devices/devicesByOwner", tested.devicesByOwner(id, model));
    }

    @Test
    public void devicesBySuperior() {
        assertEquals("devices/devicesBySuperior", tested.devicesBySuperior(id, model));
    }

    @Test
    public void devicesByAirfield() {
        assertEquals("devices/devicesByAirfield", tested.devicesByAirfield(id, model));
    }

    @Test
    public void devicesByGroup() {
        assertEquals("devices/devicesByGroup", tested.devicesByGroup(id, model));
    }

    @Test
    public void allDevicesGroupByOwner() {
        assertEquals("devices/allDevicesGroupByOwner", tested.allDevicesGroupByOwner(model));
    }

    @Test
    public void allDevicesGroupByAirfield() {
        assertEquals("devices/allDevicesGroupByAirfield", tested.allDevicesGroupByAirfield(model));
    }

    @Test
    public void allDevicesGroupByType() {
        assertEquals("devices/allDevicesCountByType", tested.allDevicesGroupByType(model));
    }

    @Test
    public void allDevicesCountByTypeGroupByGroup() {
        id = 1L;
        List<DevicesCountByType> devicesCountByTypeTest = new ArrayList<>();
        devicesCountByTypeTest.add(new DevicesCountByType());
        Group group = new Group();
        Mockito.when(groupRepository.findOne(id)).thenReturn(group);
        Mockito.when(deviceRepository.countByTypes(group)).thenReturn(devicesCountByTypeTest);

        assertEquals("redirect:/devices/group/1", tested.allDevicesCountByTypeGroupByGroup(id, model));

        devicesCountByTypeTest.add(new DevicesCountByType());
        Mockito.when(deviceRepository.countByTypes(group)).thenReturn(devicesCountByTypeTest);
        assertEquals("devices/allDevicesCountByTypeGroupByGroup", tested.allDevicesCountByTypeGroupByGroup(id, model));
    }

    @Test
    public void allDevicesGroupByTypeOrderByGroup() {
        assertEquals("devices/allDevicesCountByTypeOrderByGroup", tested.allDevicesGroupByTypeOrderByGroup(model));
    }

    @Test
    public void pivotTable() {
        assertEquals("devices/pivotTableDevicesByTypesAndOwners", tested.pivotTable(model));
    }
}