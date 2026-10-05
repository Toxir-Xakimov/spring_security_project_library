package uz.pdp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.dao.ProfileDao;
import uz.pdp.entity.Profile;
import uz.pdp.enums.Role;
import uz.pdp.service.ProfileService;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final ProfileService profileService;

    public AuthController(PasswordEncoder passwordEncoder, ProfileService profileService) {
        this.passwordEncoder = passwordEncoder;
        this.profileService = profileService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/register")
    public String register(
            @RequestParam("fullname") String fullname,
            @RequestParam("username") String username,
            @RequestParam("password") String password)
    {

        Profile profile = Profile.builder()
                .fullname(fullname)
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        boolean exists = profileService.save(profile);
        if ( !exists ) return "redirect:/auth/register";

        return "redirect:/auth/login";
    }
}
