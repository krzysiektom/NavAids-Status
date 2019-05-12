package pl.coderslab.airfield;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.owner.Owner;

import java.util.List;

@Service
@Transactional
public class AirfieldService {
    @Autowired
    private AirfieldRepository airfieldRepository;

    public Airfield findByOwner(Owner owner) {
        return airfieldRepository.findByOwner(owner);
    }

    public List<Airfield> findAll() {
        return airfieldRepository.findAll();
    }

    public Airfield findById(Long id) {
        return airfieldRepository.findOne(id);
    }
}
