package pl.coderslab;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.coderslab.airfield.Airfield;
import pl.coderslab.user.User;

import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthHandler {

    private User user;
    private boolean isLogged;

    public AuthHandler() {
    }

    public AuthHandler(User user, boolean isLogged) {
        this.user = user;
        this.isLogged = isLogged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}