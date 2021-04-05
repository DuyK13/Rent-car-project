//package com.iuh.rencar_project.service;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.iuh.rencar_project.dto.request.CategoryRequestDTO;
//import com.iuh.rencar_project.dto.response.CategoryResponseDTO;
//import com.iuh.rencar_project.entity.Category;
//import com.iuh.rencar_project.repository.CategoryRepository;
//import com.iuh.rencar_project.service.template.ICategoryService;
//import com.iuh.rencar_project.utils.DeleteStatus;
//
//@Service
//public class CategoryServiceImpl implements ICategoryService {
//
//	final static Logger logger = Logger.getLogger(CategoryServiceImpl.class);
//	
//    @Autowired
//    CategoryRepository categoryRepository;
//
//	@Override
//	public CategoryResponseDTO findByName(String name) {
//		CategoryResponseDTO result = null;
//		try {
//			Category category = categoryRepository.findByName(name);
//			result = new CategoryResponseDTO(category);
//		}catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
//		CategoryResponseDTO result = null;
//		try {
//			Category category = categoryRequestDTO.con
//		}
//		return null;
//	}
//
//	@Override
//	public boolean deleteCategoryByName(String categoryName) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public CategoryResponseDTO updateCategoryByName(String categoryName, CategoryRequestDTO categoryRequestDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Category findByNameAndStatusActive(String name) {
//		Category result = null;
//		try {
//			result = categoryRepository.findByNameAndStatusEquals(name, DeleteStatus.ACTIVE.ordinal());
//		}catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//
//}
