package pl.coderslab.owner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAllOwners() {
        List<Owner> superiors = ownerRepository.findAllBySuperior(ownerRepository.findOne(1L));
        List<Owner> ownerList = getOwners(superiors);

        return superiors.stream()
                .map(superior -> ownerList.stream()
                        .filter(owner -> owner.getSuperior().equals(superior))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Owner> getOwners(List<Owner> superiors) {
        List<Owner> ownerList = ownerRepository.findAll();
        ownerList.removeAll(superiors);
        ownerList.remove(ownerRepository.findOne(1L));
        return ownerList;
    }

    public List<OwnerBySuperior> findAllBySuperior() {
        List<Owner> superiors = ownerRepository.findAllBySuperior(ownerRepository.findOne(1L));
        List<Owner> ownerList = getOwners(superiors);
        return superiors.stream()
                .map(s -> new OwnerBySuperior(
                                s,
                                ownerList.stream()
                                        .filter(owner -> owner.getSuperior().equals(s))
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }
}
