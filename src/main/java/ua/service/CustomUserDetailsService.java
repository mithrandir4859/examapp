package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.domain.user.User;
import ua.domain.user.UserRole;
import ua.repository.UserDao;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User u = userService.find(login);
		if (u == null)
			throw new UsernameNotFoundException("User with given login " + login + " isn't found. ");
		return new CustomUserDetails(u);
	}

	private static class CustomUserDetails extends User implements UserDetails {

		private static final long serialVersionUID = 1L;
        private final static Map<UserRole, Collection<GrantedAuthority>> authoritiesMap = new HashMap<>();

        public CustomUserDetails(User user) {
            super(user);
            setId(user.getId());

            if (!authoritiesMap.containsKey(getUserRole())) {
                Collection<GrantedAuthority> authorities = new HashSet<>();
                authorities.add(new UserRoleGrantedAuthority(getUserRole()));
                authoritiesMap.put(getUserRole(), Collections.unmodifiableCollection(authorities));
            }
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authoritiesMap.get(getUserRole());
        }

        private static class UserRoleGrantedAuthority implements GrantedAuthority {
            private UserRole userRole;
            public UserRoleGrantedAuthority(UserRole userRole) {
                this.userRole = userRole;
            }

            @Override
            public String getAuthority() {
                return userRole.toString();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof UserRoleGrantedAuthority)) return false;
                UserRoleGrantedAuthority that = (UserRoleGrantedAuthority) o;
                if (userRole != that.userRole) return false;
                return true;
            }

            @Override
            public int hashCode() {
                return userRole != null ? userRole.hashCode() : 0;
            }
        }

        @Override public String getUsername() {return getLogin();}
		@Override public boolean isAccountNonExpired() {return true;}
		@Override public boolean isAccountNonLocked() {return true;}
		@Override public boolean isCredentialsNonExpired() {return true;}
		@Override public boolean isEnabled() {return true;}

	}

}
