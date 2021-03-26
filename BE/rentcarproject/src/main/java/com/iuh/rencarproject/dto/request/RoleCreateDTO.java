/**
 * @author DuyTT10
 * @date Mar 25, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.request;

import com.iuh.rencarproject.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDTO {

	private String name;
	
	public Role convertToEntity() {
		Role r = new Role();
		r.setName(name);
		return r;
	}
}

