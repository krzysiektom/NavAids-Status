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

    public Owner findById(Long id) {
        return ownerRepository.findOne(id);
    }

    public List<Owner> findAllSuperiors() {
        return ownerRepository.findAllBySuperior(ownerRepository.findOne(1L));
    }

    public List<Owner> findAllBySuperior(Owner owner) {
        return ownerRepository.findAllBySuperior(owner);
    }

    public List<Owner> findAllOwners() {
        List<Owner> superiors = findAllSuperiors();
        List<Owner> ownerList = ownerRepository.findAll();
        ownerList.removeAll(superiors);
        ownerList.remove(ownerRepository.findOne(1L));

        return superiors.stream()
                .map(superior -> ownerList.stream()
                        .filter(owner -> owner.getSuperior().equals(superior))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<OwnerBySuperior> findAllBySuperior() {
        List<Owner> superiors = findAllSuperiors();
        List<Owner> ownerList = ownerRepository.findAll();
        ownerList.removeAll(superiors);
        ownerList.remove(ownerRepository.findOne(1L));
        List<OwnerBySuperior> result = superiors.stream()
                .map(s -> new OwnerBySuperior(
                                s,
                                ownerList.stream()
                                        .filter(owner -> owner.getSuperior().equals(s))
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
        return result;
    }
}
