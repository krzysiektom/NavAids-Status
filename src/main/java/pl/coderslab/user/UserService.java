package pl.coderslab.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.coderslab.AuthHandler;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthHandler authHandler;

    public UserService(UserRepository userRepository, AuthHandler authHandler) {
        this.userRepository = userRepository;
        this.authHandler = authHandler;
    }

    boolean validateUserAndSetSession(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (null != user && BCrypt.checkpw(password, user.getPassword())) {
            setSession(user);
            return true;
        }
        return false;
    }

    void setSession(User user) {
        authHandler.setUser(user);
        authHandler.setLogged(true);
    }

    public void edit(User editUser) {
        User user = authHandler.getUser();
        user.setEmail(editUser.getEmail());
        user.setFirstName(editUser.getFirstName());
        user.setLastName(editUser.getLastName());
        userRepository.save(user);
        authHandler.setUser(user);
    }

    void  delete(User user){
        authHandler.setUser(null);
        authHandler.setLogged(false);
        userRepository.delete(user);
    }

    boolean isNotExistEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null == user;
    }

    boolean isNotExistAnotherUserWithEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null == user || authHandler.getUser().getId().equals(user.getId());
    }
}
