package pl.coderslab.airfield;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AirfieldService {
    private final AirfieldRepository airfieldRepository;

    public AirfieldService(AirfieldRepository airfieldRepository) {
        this.airfieldRepository = airfieldRepository;
    }

    public List<Airfield> findAll() {
        return airfieldRepository.findAll();
    }

    public Airfield findById(Long id) {
        return airfieldRepository.findOne(id);
    }
}
