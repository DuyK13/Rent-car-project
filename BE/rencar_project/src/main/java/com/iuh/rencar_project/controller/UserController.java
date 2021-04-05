///**
// * @author DuyTT10
// * @date Mar 23, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iuh.rencarproject.dto.request.UserRequestDTO;
//import com.iuh.rencarproject.dto.response.AppResponse;
//import com.iuh.rencarproject.dto.response.ErrorResponse;
//import com.iuh.rencarproject.dto.response.SuccessResponse;
//import com.iuh.rencarproject.dto.response.UserResponseDTO;
//import com.iuh.rencarproject.service.template.IUserService;
//import com.iuh.rencarproject.ultis.validator.UserControllerValidator;
//
//@RequestMapping("/users")
//@RestController
//@PropertySource("classpath:error.properties")
//public class UserController {
//
//	@Autowired
//	private Environment env;
//
//	@Autowired
//	private IUserService userService;
//
//	@Autowired
//	private UserControllerValidator userRequestvalidator;
//
//	@InitBinder
//	public void setup(WebDataBinder webDataBinder) {
//		webDataBinder.addValidators(userRequestvalidator);
//	}
//
//	/**
//	 * Create User. Success
//	 * 
//	 * @param userCreateDTO
//	 * @return
//	 */
//	@PostMapping()
//	public ResponseEntity<AppResponse> createUser(@Valid @RequestBody UserRequestDTO userCreateDTO,
//			BindingResult errors) {
//		if (errors.hasErrors())
//			return new ResponseEntity<>(new ErrorResponse(errors.getFieldError().getDefaultMessage()), HttpStatus.OK);
//		UserResponseDTO result = userService.createUser(userCreateDTO);
//		if (result != null)
//			return new ResponseEntity<>(new SuccessResponse<UserResponseDTO>(env.getProperty("error.user.101"), result),
//					HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.user.100")), HttpStatus.OK);
//	}
//
//	/**
//	 * Get user. Success
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> getUser(@PathVariable("id") Long userId) {
//		UserResponseDTO user = userService.findById(userId);
//		if (user != null)
//			return new ResponseEntity<>(new SuccessResponse<UserResponseDTO>(user), HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.user.102")), HttpStatus.NOT_FOUND);
//	}
//
//	/**
//	 * Get users by page. Just for test
//	 * 
//	 * @return
//	 */
//	@GetMapping()
//	public ResponseEntity<AppResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
//			@RequestParam(value = "page_size", defaultValue = "2") int pageSize) {
//		Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Order.asc("id")));
//		return new ResponseEntity<>(
//				new SuccessResponse<List<UserResponseDTO>>(userService.findAllPaging(pageable)),
//				HttpStatus.OK);
//	}
//
////	/**
////	 * Get users. Success
////	 * 
////	 * @return
////	 */
////	@GetMapping()
////	public ResponseEntity<AppResponse> getUsers() {
////		return new ResponseEntity<>(new SuccessResponse<List<UserResponseDTO>>(userService.findAll()), HttpStatus.OK);
////	}
//
//	/**
//	 * Delete user. Success
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> deleteUser(@PathVariable("id") Long userId) {
//		boolean flag = userService.deleteById(userId);
//		if (flag)
//			return new ResponseEntity<>(new SuccessResponse<UserResponseDTO>(env.getProperty("error.user.104")),
//					HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.user.103")), HttpStatus.NOT_FOUND);
//	}
//
//	/**
//	 * Update user
//	 * 
//	 * @param userId
//	 * @param userUpdateDTO
//	 * @return
//	 */
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> updateUser(@PathVariable("id") Long userId,
//			@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult errors) {
//		if (errors.hasErrors())
//			return new ResponseEntity<>(new ErrorResponse(errors.getFieldError().getDefaultMessage()), HttpStatus.OK);
//		UserResponseDTO user = userService.updateUser(userId, userRequestDTO);
//		if (user != null)
//			return new ResponseEntity<>(
//					new SuccessResponse<UserResponseDTO>(env.getProperty("error.user.106"), user),
//					HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.user.105")), HttpStatus.OK);
//	}
//}
