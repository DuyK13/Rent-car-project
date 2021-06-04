package com.iuh.rencar_project.auditing;

import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * The type Auditor aware.
 *
 * @author Trần Thế Duy
 * @version 0.1
 * @datetime May 1, 2021 3:03:02 PM
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated())
            return Optional.empty();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }

}
