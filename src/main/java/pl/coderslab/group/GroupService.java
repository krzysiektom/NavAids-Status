package pl.coderslab.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

}
