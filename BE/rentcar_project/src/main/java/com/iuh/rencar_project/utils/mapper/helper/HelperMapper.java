package com.iuh.rencar_project.utils.mapper.helper;

import com.iuh.rencar_project.entity.*;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.StringUtils;
import com.iuh.rencar_project.utils.enums.BillType;
import com.iuh.rencar_project.utils.enums.ERole;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.mapper.annotation.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Trần Thế Duy
 * @version 0.1
 * @datetime May 1, 2021 3:03:02 PM
 */
@Component
public class HelperMapper {

    private PasswordEncoder passwordEncoder;

    private IRoleService roleService;

    private ITagService tagService;

    private ICommentService commentService;

    private ICategoryService categoryService;

    private ICourseService courseService;

    private IBillService billService;

    private ICarService carService;

    private IPostService postService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCarService(ICarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    @Autowired
    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setTagService(ITagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @StringToRoleMapping
    public Role toRole(String value) {
        return roleService.findByName(Enum.valueOf(ERole.class, value));
    }

    @RoleToStringMapping
    public String toString(Role role) {
        return role.getName().name();
    }

    @StringToSlugMapping
    public String toString(String value) {
        return StringUtils.unAccent(value);
    }

    @UserToStringMapping
    public String toString(User user) {
        if (user == null)
            return null;
        return user.getUsername();
    }

    @PasswordEncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }

    @StringToTagMapping
    public Tag toTag(String value) {
        return tagService.findByName(value);
    }

    @TagToStringMapping
    public String toString(Tag tag) {
        return tag.getName();
    }

    @LongToCommentMapping
    public Comment toComment(Long id) {
        if (id == null)
            return null;
        return commentService.findById(id);
    }

    @StringToCategoryMapping
    public Category toCategory(String name) {
        if (Strings.isEmpty(name))
            return null;
        return categoryService.findByName(name);
    }

    @StringToCourseMapping
    public Course toCourse(String title) {
        if (Strings.isEmpty(title))
            return null;
        return courseService.findByTitle(title);
    }

    @StringToCarMapping
    public Car toCar(String name) {
        if (Strings.isEmpty(name))
            return null;
        return carService.findByName(name);
    }

    @BillSlugMapping
    public String toBillSlug(String fullname) {
        return passwordEncoder.encode(billService.getCurrentId() + "").replace("/", "");
    }

    @CommentLevelMapping
    public int toLevel(Long id) {
        if (Objects.isNull(id)) {
            return 0;
        } else {
            Comment comment = commentService.findById(id);
            if (comment.getLevel() == Comment.MAX_LEVEL)
                throw new EntityException("Can not reply comment");
            else
                return comment.getLevel() + 1;
        }
    }

    @StringToPostMapping
    public Post toPost(String title){
        return postService.findByTitle(title);
    }

    @StringToTypeNameMapping
    public BillType toBillType(String type){
        if(type.equalsIgnoreCase(BillType.SA_HINH.getName()))
            return BillType.SA_HINH;
        else if(type.equalsIgnoreCase(BillType.DUONG_TRUONG.getName()))
            return BillType.DUONG_TRUONG;
        else if(type.equalsIgnoreCase(BillType.LIEN_TINH.getName()))
            return BillType.LIEN_TINH;
        return null;
    }

    @BillTypeToStringMapping
    public String toStringBillType(BillType type){
        if(Objects.isNull(type))
            return null;
        return type.getName();
    }
}
