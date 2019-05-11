package pl.coderslab.failure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.fix.Fix;
import pl.coderslab.fix.FixRepository;

import java.util.List;

@Service
@Transactional
public class FailureService {
    @Autowired
    FailureRepository failureRepository;
    @Autowired
    FixRepository fixRepository;

    public List<Failure> findAll() {
        return failureRepository.findAll();
    }
    public Failure findOne(Long id) {
        return failureRepository.findOne(id);
    }

    public List<Fix> findAllFixesByFailure(Failure failure) {
    return fixRepository.findAllByFailure(failure);
    }
}
