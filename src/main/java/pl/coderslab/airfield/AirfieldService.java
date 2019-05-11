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

    public Airfield findAirfieldByOwner(Owner owner) {
        return airfieldRepository.findByOwner(owner);
    }

    public List<Airfield> findAllAirfields() {
        return airfieldRepository.findAll();
    }

}
