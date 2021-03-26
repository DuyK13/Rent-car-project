/**
 * @author DuyTT10
 * @date Mar 25, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.response;

import com.iuh.rencarproject.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDTO {

	private Long id;
	private String name;
	
	public RoleResponseDTO(Role r) {
		this(r.getId(), r.getName());
	}
}

