/**
 * @author trant
 * @created_date Apr 21, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.request;

import java.io.Serializable;
import java.util.List;

import com.iuh.rencar_project.utils.enums.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO implements Serializable {
	static final long serialVersionUID = 2652264316377550947L;

	Long id;

	String username;

	String password;

	int status;

	List<String> roles;

	public UserRequestDTO(Long id, String username, String password, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = Status.ACTIVE.ordinal();
		this.roles = roles;
	}

}
