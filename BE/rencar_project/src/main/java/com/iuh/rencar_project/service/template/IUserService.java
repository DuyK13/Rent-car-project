/**
 * @author trant
 * @created_date Apr 11, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service.template;

import org.springframework.data.domain.Page;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.entity.User;

public interface IUserService {
	/**
	 * Save {@link User}
	 * 
	 * @param userRequest
	 * @return {@link String}
	 */
	String save(UserRequest userRequest);

	/**
	 * Update {@link User}
	 * 
	 * @param id
	 * @param userRequest
	 * @return {@link String}
	 */
	String update(Long id, UserRequest userRequest);

	String update(Long id);

	/**
	 * Exists {@link User} by username
	 * 
	 * @param username
	 * @return {@link Boolean}
	 */
	Boolean existsByUsername(String username);

	User findById(Long id);

	User findByUsername(String username);

	Page<User> findAllPaginated(int pageNo);

	String delete(Long id);

	String changePassword(Long id, PasswordRequest passwordRequest);

	Boolean isRightPassword(String username, String password);

}
