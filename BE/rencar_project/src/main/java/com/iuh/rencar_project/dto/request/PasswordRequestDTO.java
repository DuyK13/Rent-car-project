/**
 * @author trant
 * @created_date Apr 21, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.request;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordRequestDTO implements Serializable {

	private static final long serialVersionUID = -7728676410197370113L;

	Long id;
	
	String oldPassword;

	String newPassword;
}
