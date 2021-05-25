/*
  @author trant
 * @created_date Apr 11, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.entity.User;
import org.springframework.data.domain.Page;

/**
 * The interface User service.
 */
public interface IUserService {
    /**
     * Save string.
     *
     * @param userRequest the user request
     * @return the string
     */
    String save(UserRequest userRequest);

    /**
     * Update string.
     *
     * @param id          the id
     * @param userRequest the user request
     * @return the string
     */
    String update(Long id, UserRequest userRequest);

    /**
     * Update string.
     *
     * @param id the id
     * @return the string
     */
    String update(Long id);

    /**
     * Exists by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    Boolean existsByUsername(String username);

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(Long id);

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find all paginated page.
     *
     * @param pageNo the page no
     * @return the page
     */
    Page<User> findAllPaginated(int pageNo);

    /**
     * Delete string.
     *
     * @param id the id
     * @return the string
     */
    String delete(Long id);

    /**
     * Change password string.
     *
     * @param id              the id
     * @param passwordRequest the password request
     * @return the string
     */
    String changePassword(Long id, PasswordRequest passwordRequest);

    /**
     * Is right password boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    Boolean isRightPassword(String username, String password);

    Boolean isUserActive(String username);

    boolean existsByEmail(String email);
}
