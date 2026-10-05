package uz.pdp.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.pdp.dao.ProfileDao;
import uz.pdp.entity.Profile;

import java.util.Optional;

@Service
@Component
public class ProfileService {


    private final ProfileDao profileDao;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileDao profileDao, PasswordEncoder passwordEncoder) {
        this.profileDao = profileDao;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean save(Profile profile) {
        Optional<Profile> exists = profileDao.findByUsername(profile.getUsername());

        if ( exists.isPresent() ) return false;

        profileDao.save(profile);
        return true;
    }

    public boolean authentication(String username, String password) {
        Optional<Profile> byUsername = profileDao.findByUsername(username);

        if ( byUsername.isPresent() ) {
            Profile profile = byUsername.get();

            return passwordEncoder.matches(password, profile.getPassword());
        }

        return false;
    }
}
