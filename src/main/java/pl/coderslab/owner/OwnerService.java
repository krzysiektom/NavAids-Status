package pl.coderslab.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    public List<Owner> findAllSuperiors() {
        return ownerRepository.findAllBySuperior(ownerRepository.findOne(1L));
    }

    public List<Owner> findAllOwnersBySuperior(Owner superior) {
        return ownerRepository.findAllBySuperior(superior);
    }

    public List<Owner> findAllOwners() {
        return findAllSuperiors().stream()
                .map(owner -> ownerRepository.findAllBySuperior(owner))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
