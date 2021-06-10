package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.TagRequest;
import com.iuh.rencar_project.entity.Post;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.repository.TagRepository;
import com.iuh.rencar_project.service.template.IPostService;
import com.iuh.rencar_project.service.template.ITagService;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ITagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements ITagService {

    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository tagRepository;

    private final ITagMapper tagMapper;

    private final IPostService postService;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, ITagMapper tagMapper, IPostService postService) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.postService = postService;
    }

    @Override
    public String save(TagRequest tagRequest) {
        String name = tagRequest.getName();
        if (this.existsByName(name))
            throw new EntityException("Tag exists");
        try {
            tagRepository.saveAndFlush(tagMapper.toEntity(tagRequest));
        } catch (Exception e) {
            logger.error("Tag Exception: ", e);
            throw new EntityException("Tag save failed", e);
        }
        return "Tag save successful";
    }

    @Override
    public String update(Long id, TagRequest tagRequest) {
        Tag currentTag = this.findById(id);
        String name = currentTag.getName();
        if (this.existsByName(tagRequest.getName()) && !name.equals(tagRequest.getName()))
            throw new EntityException("Tag exists");
        tagMapper.updateEntity(tagRequest, currentTag);
        try {
            tagRepository.saveAndFlush(currentTag);
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            throw new EntityException("Tag update failed", e);
        }
        return "Tag update successful";
    }

    @Override
    public Page<Tag> findAllPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Order.asc("id")));
        return tagRepository.findAll(pageable);
    }

    @Override
    public String delete(Long id) {
        Tag tag = this.findById(id);
        List<Post> posts = postService.findByTag(tag);
        for (Post post : posts) {
            Set<Tag> tags = post.getTags();
            tags.remove(tag);
            post.setTags(tags);
        }
        try {
            tagRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("Tag delete failed");
        }
        return "Tag delete successful";
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag not found"));
    }

    @Override
    public Tag findBySlug(String slug) {
        return tagRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Tag not found"));
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Tag not found"));
    }

    @Override
    public Boolean existsByName(String name) {
        return tagRepository.existsByName(name);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> search(int pageNo, int pageSize, String s) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, Sort.by(Order.asc("id")));
        return tagRepository.search(s, pageable);
    }
}
