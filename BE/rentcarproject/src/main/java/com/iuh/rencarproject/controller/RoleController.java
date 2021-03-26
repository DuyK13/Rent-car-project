/**
 * @author DuyTT10
 * @date Mar 25, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.rencarproject.dto.request.RoleCreateDTO;
import com.iuh.rencarproject.dto.response.AppResponse;
import com.iuh.rencarproject.dto.response.ErrorResponse;
import com.iuh.rencarproject.dto.response.RoleResponseDTO;
import com.iuh.rencarproject.dto.response.SuccessResponse;
import com.iuh.rencarproject.entity.Role;
import com.iuh.rencarproject.service.template.IRoleService;

@RequestMapping("/roles")
@RestController
public class RoleController {

	@Autowired
	IRoleService roleService;
	
	/**
	 * Add role. Success
	 * 
	 * @param roleCreateDTO
	 * @return ResponseEntity<AppResponse>
	 */
	@PostMapping
	public ResponseEntity<AppResponse> addRole(@Valid @RequestBody RoleCreateDTO roleCreateDTO){
		Role role = roleService.addRole(roleCreateDTO);
		if (role != null)
			return new ResponseEntity<>(new SuccessResponse<RoleResponseDTO>(new RoleResponseDTO(role)),
					HttpStatus.OK);
		return new ResponseEntity<>(new ErrorResponse("{error.role.100}"), HttpStatus.OK);
	}
	
	/**
	 * Get roles. Success
	 * 
	 * @return ResponseEntity<AppResponse>
	 */
	@GetMapping
	public ResponseEntity<AppResponse> getRoles(){
		List<RoleResponseDTO> roles = roleService.findAll().stream().map(x->{
			return new RoleResponseDTO(x);
		}).collect(Collectors.toList());
			return new ResponseEntity<>(new SuccessResponse<List<RoleResponseDTO>>(roles),
					HttpStatus.OK);
	}
}

