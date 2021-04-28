/**
 * @author trant
 * @created_date Apr 11, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service.template;

import java.util.List;

import org.springframework.data.domain.Page;

import com.iuh.rencar_project.dto.request.PasswordRequestDTO;
import com.iuh.rencar_project.dto.request.UserRequestDTO;
import com.iuh.rencar_project.entity.User;

public interface IUserService {

	User findById(Long id);

	List<User> findAll();
	
	Page<User> findPaginated(int page, int size);

	boolean existsByUsername(String username);
	
	boolean existsByIdAndPassword(Long id, String password);

	String save(UserRequestDTO userRequestDTO);

	String updateById(Long id);

	String updateById(Long id, UserRequestDTO userRequestDTO);
	
	String updatePasswordById(Long id, PasswordRequestDTO passwordRequestDTO);

	String deleteById(Long id);
}
