/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service.template;

import java.util.List;

import com.iuh.rencar_project.dto.request.RoleRequest;
import com.iuh.rencar_project.entity.Role;

public interface IRoleService {

	/**
	 * Save {@link Role}
	 * 
	 * @param roleRequest
	 * @return {@link String}
	 */
	String save(RoleRequest roleRequest);

	/**
	 * Find {@link Role} by Id
	 * 
	 * @param id
	 * @return {@link Role}
	 */
	Role findById(Long id);

	/**
	 * Exists {@link Role} by name
	 * 
	 * @param name
	 * @return {@link Boolean}
	 */
	Boolean existsByName(String name);

	/**
	 * Find {@link Role} by name
	 * 
	 * @param name
	 * @return {@link Role}
	 */
	Role findByName(String name);
	
	/**
	 * Find all {@link Role}
	 * @return List
	 */
	List<Role> findAll();
}
