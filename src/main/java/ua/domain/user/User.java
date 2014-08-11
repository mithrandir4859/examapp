package ua.domain.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Adevi on 8/9/2014.
 */
@Entity
public class User implements Serializable{
    private Long id;
    private String login;
    private String firstname;
    private String lastname;
    private UserRole userRole;

    /*
    Well, I know this is a bad practice - to store password
    just like usual String in user table with no encoding or hashing.
    I'm doing this just for simplicity.
    */
    private String password;

    public User() {
    }

    public User(String login, String firstname, String lastname, UserRole userRole, String password) {
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userRole = userRole;
        this.password = password;
    }

    public User(User user) {
        this.login = user.login;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.userRole = user.userRole;
        this.password = user.password;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
