package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.TagRequest;
import com.iuh.rencar_project.entity.Tag;
import org.springframework.data.domain.Page;

public interface ITagService {

	String save(TagRequest tagRequest);
	
	String update(Long id, TagRequest tagRequest);
	
	String delete(Long id);
	
	Tag findById(Long id);
	
	Tag findBySlug(String slug);

	Tag findByName(String name);

	Page<Tag> findAllPaginated(int pageNo);
	
	Boolean existsByName(String name);
}
