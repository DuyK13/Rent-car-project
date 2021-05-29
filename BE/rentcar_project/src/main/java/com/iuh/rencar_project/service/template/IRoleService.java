/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.utils.enums.ERole;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    Role findByName(ERole name);
}
