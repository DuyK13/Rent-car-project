package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.PostRequest;
import com.iuh.rencar_project.entity.Post;
import org.springframework.data.domain.Page;

public interface IPostService {
    String save(PostRequest postRequest);

    String update(Long id, PostRequest postRequest);

    String update(Long id);

    String delete(Long id);

    Post findById(Long id);

    Post findBySlug(String slug);

    Page<Post> findAllPaginated(int pageNo);

    Boolean existsByTitle(String title);
}
