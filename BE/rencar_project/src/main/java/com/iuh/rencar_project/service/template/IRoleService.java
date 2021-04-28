/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service.template;

import java.util.List;

import com.iuh.rencar_project.dto.RoleDTO;
import com.iuh.rencar_project.entity.Role;

public interface IRoleService {

	Role findById(Long id);
	
	Role findByName(String name);
	
	List<Role> findAll();
	
	boolean saveAndUpdate(RoleDTO roleDTO);
	
	
}
