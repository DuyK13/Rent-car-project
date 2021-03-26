/**
 * @author DuyTT10
 * @date Mar 23, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.service.template;

import java.util.List;

import com.iuh.rencarproject.dto.request.RoleCreateDTO;
import com.iuh.rencarproject.entity.Role;

public interface IRoleService {
	
	Role findByName(String roleName);

	List<Role> findAll();

	Role addRole(RoleCreateDTO roleCreateDTO);
}
