package com.resort.Service;

import com.resort.entity.User;
import com.resort.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        // Validation for admin email format
        if (user.getUserType() == User.UserType.ROLE_ADMIN && !user.getEmail().endsWith("@admin.com")) {
            throw new IllegalArgumentException("Admin emails must end with @admin.com.");
        }

        // Hash the user password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        // Get the current authenticated user details
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;

        // Check if the principal is a UserDetails object
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            // Handle the case where the principal is a String (e.g., anonymous user)
            username = principal.toString();
        }

        // Retrieve the user from the database based on the username
        User currentUser = userRepository.findByUsername(username);

        // Check if the current user has the "ADMIN" role
        if (currentUser.getUserType() != User.UserType.ROLE_ADMIN) {
            throw new AccessDeniedException("You are not authorized to access this resource.");
        }

        // If the user has the "ADMIN" role, return the list of all users
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserType().name()))
        );
    }
}