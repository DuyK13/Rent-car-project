package com.iuh.rencar_project.service.template;

import com.iuh.rencar_project.dto.request.PostRequest;
import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostService {
    String save(PostRequest postRequest, MultipartFile multipartFile);

    String update(Long id, PostRequest postRequest, MultipartFile multipartFile);

    String update(Long id, PostRequest postRequest);

    String setAvailability(Long id);

    String delete(Long id);

    Post findById(Long id);

    Post findBySlug(String slug);

    Post findBySlugForGuest(String slug);

    Page<Post> findAllPaginated(int pageNo, int pageSize);

    Page<Post> findAllPaginatedForGuest(int pageNo, int pageSize);

    Page<Post> findAllPaginatedByTagForGuest(Tag tag, int pageNo, int pageSize);

    Boolean existsByTitle(String title);

    List<Post> findByTag(Tag tag);

    Post findByTitle(String title);

    Page<Post> search(int pageNo, int pageSize, String s);
}
