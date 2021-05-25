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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(TagServiceImpl.class);

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
            throw new EntityException("Tag " + name + " exists");
        try {
            tagRepository.saveAndFlush(tagMapper.toEntity(tagRequest));
        } catch (Exception e) {
            logger.error("Tag Exception: ", e);
            throw new EntityException("Tag " + name + " save fail", e);
        }
        return "Tag " + name + " save success";
    }

    @Override
    public String update(Long id, TagRequest tagRequest) {
        Tag currentTag = this.findById(id);
        String name = currentTag.getName();
        if (this.existsByName(tagRequest.getName()) && !name.equals(tagRequest.getName()))
            throw new EntityException("Tag " + name + " exists");
        try {
            tagMapper.updateEntity(tagRequest, currentTag);
            tagRepository.saveAndFlush(currentTag);
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            return "Tag " + name + " update fail";
        }
        return "Tag " + name + " update success";
    }

    @Override
    public Page<Tag> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Order.asc("id")));
        return tagRepository.findAll(pageable);
    }

    @Override
    public String delete(Long id) {
        Tag tag = this.findById(id);
        try {
            List<Post> posts = postService.findPostsByTag(tag);
            for (Post post : posts) {
                Set<Tag> tags = post.getTags();
                tags.remove(tag);
                post.setTags(tags);
            }
            tagRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EntityException("Tag " + tag.getName() + " delete fail");
        }
        return "Tag " + tag.getName() + " delete success";
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Tag with id " + id + " not found"));
    }

    @Override
    public Tag findBySlug(String slug) {
        return tagRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Tag with slug " + slug + " not found"));
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Tag with name " + name + " not found"));
    }

    @Override
    public Boolean existsByName(String name) {
        return tagRepository.existsByName(name);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
