package ua.util;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.domain.user.User;

/**
 * Created by Adevi on 8/10/2014.
 */
public class Util {
    public static User currentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
