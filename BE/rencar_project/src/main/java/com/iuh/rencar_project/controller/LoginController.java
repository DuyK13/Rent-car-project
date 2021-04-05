///**
// * @author DuyTT10
// * @date Mar 25, 2021
// * @version 1.0
// */
//
//package com.iuh.rencar_project.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iuh.rencarproject.dto.request.UserRequestDTO;
//import com.iuh.rencarproject.dto.response.AppResponse;
//import com.iuh.rencarproject.dto.response.ErrorResponse;
//import com.iuh.rencarproject.dto.response.SuccessResponse;
//import com.iuh.rencarproject.dto.response.UserResponseDTO;
//import com.iuh.rencarproject.service.template.IUserService;
//import com.iuh.rencarproject.ultis.validator.LoginControllerValidator;
//
//@RestController
//@PropertySource("classpath:error.properties")
//public class LoginController {
//
//	@Autowired
//	private IUserService userService;
//	
//	@Autowired
//	private Environment env;
//	
//	@Autowired
//	private LoginControllerValidator loginControllerValidator;
//
//	@InitBinder
//	public void setup(WebDataBinder webDataBinder) {
//		webDataBinder.addValidators(loginControllerValidator);
//	}
//	/**
//	 * Login 
//	 * 
//	 * @param userLoginDTO
//	 * @return
//	 */
//	@PostMapping("/login")
//	public ResponseEntity<AppResponse> login(@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult errors) {
//		if(errors.hasErrors())
//			return new ResponseEntity<>(new ErrorResponse(errors.getFieldError().getDefaultMessage()), HttpStatus.OK);
//		UserResponseDTO user = userService.getDetailByLogin(userRequestDTO.getUsername(), userRequestDTO.getPassword());
//		if (user != null)
//			return new ResponseEntity<>(new SuccessResponse<UserResponseDTO>(user),
//					HttpStatus.OK);
//		return new ResponseEntity<AppResponse>(new ErrorResponse(env.getProperty("error.user.107")),
//				HttpStatus.BAD_REQUEST);
//	}
//}
