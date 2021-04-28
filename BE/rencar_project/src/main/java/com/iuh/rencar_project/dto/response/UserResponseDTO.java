/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.response;

import java.io.Serializable;
import java.util.List;

import com.iuh.rencar_project.dto.RoleDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO implements Serializable {

	static final long serialVersionUID = 2652264316377550947L;

	Long id;

	String username;

	String password;

	int status;

	List<RoleDTO> roles;
}
