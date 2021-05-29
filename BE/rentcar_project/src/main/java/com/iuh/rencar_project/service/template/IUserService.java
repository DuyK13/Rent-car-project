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
    String save(UserRequest userRequest);

    String updateUserEmail(Long id, UserRequest userRequest);

    String setAvailability(Long id);

    Boolean existsByUsername(String username);

    User findById(Long id);

    User findByUsername(String username);

    String delete(Long id);

    String changePassword(Long id, PasswordRequest passwordRequest);

    Boolean isCorrectPassword(String username, String password);

    Boolean isUserEnable(String username);

    Page<User> findAllPaginated(int pageNo);
}
