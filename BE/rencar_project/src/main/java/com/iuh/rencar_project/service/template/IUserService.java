///**
// * @author DuyTT10
// * @date Mar 23, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.service.template;
//
//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
//
//import com.iuh.rencar_project.dto.request.UserRequestDTO;
//import com.iuh.rencar_project.dto.response.UserResponseDTO;
//import com.iuh.rencar_project.entity.User;
//
//public interface IUserService {
//
//	UserResponseDTO createUser(UserRequestDTO userRequestDTO);
//
//	UserResponseDTO findById(Long id);
//
//	UserResponseDTO getDetailByLogin(String username, String password);
//
//	boolean existsByUsername(String username);
//
//	boolean existsByPhoneNumberNotNull(String phoneNumber);
//
//	boolean existsByEmailAndEmailNotNull(String email);
//
//	boolean checkUserPassword(String username, String password);
//
//	boolean deleteById(Long id);
//
//	List<UserResponseDTO> findAll();
//
//	List<UserResponseDTO> findAllPaging(Pageable pageRequest);
//
//	User findByUserName(String username);
//
//	UserResponseDTO updateUser(Long userid, UserRequestDTO userRequestDTO);
//
//}
