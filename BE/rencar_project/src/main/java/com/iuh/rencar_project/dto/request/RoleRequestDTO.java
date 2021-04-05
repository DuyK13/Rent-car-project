/**
 * @author DuyTT10
 * @date Mar 25, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.request;

import com.iuh.rencar_project.entity.Role;

import lombok.Data;

@Data
public class RoleRequestDTO {

	private String name;
	
	public Role convertRoleCreateToEntity() {
		Role r = new Role();
		r.setName(name);
		return r;
	}
}

