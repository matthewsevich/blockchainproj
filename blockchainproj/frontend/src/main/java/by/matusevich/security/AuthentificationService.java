package by.matusevich.security;

import by.matusevich.pojo.AppUser;
import by.matusevich.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("authService")
public class AuthentificationService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(AuthentificationService.class);

    @Autowired
    AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.info("Calling loadUserByUsername: {}", username);
        AppUser appUser = appUserService.findByUserName(username);

        if (appUser == null) throw new UsernameNotFoundException("User not found " + username);

        return new User(
                appUser.getUserName(),
                appUser.getUserPassword(),
                appUser.getRoles()
                        .stream()
                        .map(appRole -> new SimpleGrantedAuthority("ROLE_"+ appRole.getRoleName()))
                        .collect(Collectors.toList()));

    }
}
