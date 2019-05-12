package pl.coderslab.device;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DeviceDTO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Device> getAll() {
        Query query = entityManager.createQuery("select d from Device d");
        List<Device> allDevices = query.getResultList();
        return allDevices;
    }
}
