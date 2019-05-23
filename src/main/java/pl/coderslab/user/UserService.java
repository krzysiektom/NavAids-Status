package pl.coderslab.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.AuthHandler;

import java.util.List;

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

    boolean isNotExistEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null == user;
    }

    boolean isNotExistAnotherUserWithEmail(User userValidate) {
        User user = userRepository.findByEmail(userValidate.getEmail());
        return null == user || authHandler.getUser().getId().equals(user.getId());
    }

}
