package com.paravar;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserUserDetailsService implements UserDetailsService {

    private final AppUserRepository userRepository;

    public CustomUserUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    // this UserDetails will act like @AuthenticationPrincipal ( Principal )
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser customUser = this.userRepository.findCustomUserByEmail(username);
        if (customUser == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new CustomUserDetails(customUser);
    }
}
