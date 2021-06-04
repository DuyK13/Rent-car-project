package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.repository.UserRepository;
import com.iuh.rencar_project.service.template.IRoleService;
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
    private final IRoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, IUserMapper userMapper, IRoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }


    @Override
    public String save(UserRequest userRequest) {
        String username = userRequest.getUsername();
        if (userRepository.existsByUsername(username))
            throw new EntityException("User exists");
        try {
            userRepository.saveAndFlush(userMapper.createUser(userRequest));
        } catch (Exception e) {
            logger.error("User Exception: ", e);
            throw new EntityException("User save failed", e);
        }
        return "User save successful";
    }

    @Override
    public String updateUserEmail(Long id, UserRequest userRequest) {
        User currentUser = this.findById(id);
        if ((this.existsByUsername(userRequest.getUsername()) && !currentUser.getUsername().equals(userRequest.getUsername())) || (userRepository.existsByEmail(userRequest.getEmail()) && !currentUser.getEmail().equals(userRequest.getEmail())))
            throw new EntityException("User exists");
        currentUser.setEmail(userRequest.getEmail());
        try {
            userRepository.saveAndFlush(currentUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("User update failed", e);
        }
        return "User update successful";
    }

    @Override
    public String setAvailability(Long id) {
        User currentUser = this.findById(id);
        String message;
        if (currentUser.getStatus() == Status.ENABLE) {
            currentUser.setStatus(Status.DISABLE);
            message = "Disable user successful";
        } else {
            currentUser.setStatus(Status.ENABLE);
            message = "Enable user successful";
        }
        try {
            userRepository.saveAndFlush(currentUser);
        } catch (Exception e) {
            logger.error("User Exception: ", e);
            throw new EntityException("User status change failed");
        }
        return message;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
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
    public Page<User> findAllPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Order.asc("id")));
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
            throw new EntityException("User change password failed");
        }
        return "User change password successful";
    }

    @Override
    public String delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("User delete failed");
        }
        return "User delete successful";
    }

    @Override
    public Boolean isCorrectPassword(String username, String password) {
        User currentUser = this.findByUsername(username);
        return passwordEncoder.matches(password, currentUser.getPassword());
    }

    @Override
    public Boolean isUserEnable(String username) {
        User currentUser = this.findByUsername(username);
        return currentUser.getStatus().compareTo(Status.ENABLE) == 0;
    }

    @Override
    public Page<User> search(int pageNo, int pageSize, String s) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Order.asc("id")));
        return userRepository.search(s, pageable);
    }

    @DependsOn("initRole")
    @PostConstruct
    public void initUser() {
        if (!this.existsByUsername("admin")) {
            Set<Role> roles = new HashSet<>(Collections.singletonList(roleService.findByName(ERole.ROLE_ADMIN)));
            userRepository
                    .saveAndFlush(new User(0L, "admin", passwordEncoder.encode("admin"), null, Status.ENABLE, roles));
        }
    }
}
