package by.matusevich.service;

import by.matusevich.repo.AppUserDao;
import by.matusevich.pojo.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppUserService {

    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    @Value("#{appUserRepository}")
    AppUserDao<AppUser> appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void createNewUser(AppUser appUser) {
        final String encodedPassword = passwordEncoder.encode(appUser.getUserPassword());
        log.info("Save a new user with name and password: {} {} ",
                appUser.getUserName(), encodedPassword
        );

        appUser.setUserPassword(encodedPassword);
        appUserRepository.create(appUser);
    }

    @Transactional
    public AppUser findByUserName(String username) {
        return appUserRepository.find(username);
    }

    @Transactional
    public static String getAppUserUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
