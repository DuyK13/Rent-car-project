package com.iuh.rencar_project.service.template;

import org.springframework.data.domain.Page;

import com.iuh.rencar_project.dto.request.TagRequest;
import com.iuh.rencar_project.entity.Tag;

public interface ITagService {

	String save(TagRequest tagRequest);
	
	String update(Long id, TagRequest tagRequest);
	
	String delete(Long id);
	
	Tag findById(Long id);
	
	Tag findBySlug(String slug);
	
	Page<Tag> findAllPaginated(int pageNo);
	
	Boolean existsByName(String name);
}
