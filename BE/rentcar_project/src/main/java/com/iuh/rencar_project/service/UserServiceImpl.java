package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.repository.UserRepository;
import com.iuh.rencar_project.service.template.IUserService;
import com.iuh.rencar_project.utils.enums.ERole;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IUserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:09 AM
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IUserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public String save(UserRequest userRequest) {
        String username = userRequest.getUsername();
        if (this.existsByUsername(username))
            throw new EntityException("User exists");
        try {
            userRepository.saveAndFlush(userMapper.toEntity(userRequest));
        } catch (Exception e) {
            logger.error("User Exception: ", e);
            throw new EntityException("User save fail", e);
        }
        return "User save success";
    }

    @Override
    public String update(Long id, UserRequest userRequest) {
        User currentUser = this.findById(id);
        if ((this.existsByUsername(userRequest.getUsername()) && !currentUser.getUsername().equals(userRequest.getUsername())) || (this.existsByEmail(userRequest.getEmail()) && !currentUser.getEmail().equals(userRequest.getEmail())))
            throw new EntityException("User exists");
        userMapper.updateUserInformation(userRequest, currentUser);
        try {
            userRepository.saveAndFlush(currentUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("User update fail", e);
        }
        return "User update success";
    }

    @Override
    public String update(Long id) {
        User currentUser = this.findById(id);
        String message;
        if (currentUser.getStatus() == Status.ACTIVE) {
            currentUser.setStatus(Status.INACTIVE);
            message = "User deactivate success";
        } else {
            currentUser.setStatus(Status.ACTIVE);
            message = "User activate success";
        }
        try {
            userRepository.saveAndFlush(currentUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("User change status fail");
        }
        return message;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public Page<User> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Order.asc("id")));
        return userRepository.findAll(pageable);
    }

    @Override
    public String changePassword(Long id, PasswordRequest passwordRequest) {
        User currentUser = this.findById(id);
        if (!passwordEncoder.matches(passwordRequest.getOldPassword(), currentUser.getPassword())) {
            throw new EntityException("User wrong old password");
        }
        currentUser.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        try {
            userRepository.saveAndFlush(currentUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("User change password fail");
        }
        return "User change password success";
    }

    @Override
    public String delete(Long id) {
        User user = this.findById(id);
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("User delete fail");
        }
        return "User delete success";
    }

    @Override
    public Boolean isRightPassword(String username, String password) {
        User currentUser = this.findByUsername(username);
        return passwordEncoder.matches(password, currentUser.getPassword());
    }

    @Override
    public Boolean isUserActive(String username) {
        User currentUser = this.findByUsername(username);
        return currentUser.getStatus().compareTo(Status.ACTIVE) == 0;
    }

    @DependsOn("initRole")
    @PostConstruct
    public void initUser() {
        if (!this.existsByUsername("admin")) {
            Set<Role> roles = new HashSet<>(Collections.singletonList(new Role(1L, ERole.ROLE_ADMIN)));
            userRepository
                    .saveAndFlush(new User(1L, "admin", passwordEncoder.encode("admin"), null, Status.ACTIVE, roles));
        }
    }
}
