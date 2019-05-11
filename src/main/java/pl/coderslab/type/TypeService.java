package pl.coderslab.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.group.Group;

import java.util.List;

@Service
@Transactional
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public Type findTypeById(Long id) {
        return typeRepository.findOne(id);
    }

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public List<Type> findAllTypesByGroup(Group group) {
        return typeRepository.findAllByGroup(group);
    }

    public List<Type> findAllByGroup(Group group) {
        return typeRepository.findAllByGroup(group);
    }
}
