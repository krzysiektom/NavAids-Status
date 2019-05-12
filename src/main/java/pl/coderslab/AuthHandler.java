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

    private Long id;
    private boolean isLogged;
    private List<Airfield> airfields;

    public AuthHandler() {
    }

    public AuthHandler(Long id, boolean isLogged) {
        this.id = id;
        this.isLogged = isLogged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return new User(id);
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public List<Airfield> getAirfields() {
        return airfields;
    }

    public void setAirfields(List<Airfield> airfields) {
        this.airfields = airfields;
    }
}