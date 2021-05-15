package com.iuh.rencar_project.utils.mapper.helper;

import com.iuh.rencar_project.entity.*;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.StringUtils;
import com.iuh.rencar_project.utils.enums.CarType;
import com.iuh.rencar_project.utils.mapper.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Trần Thế Duy
 * @version 0.1
 * @datetime May 1, 2021 3:03:02 PM
 */
@Component
public class HelperMapper {

    private IRoleService roleService;

    private ITagService tagService;

    private ICommentService commentService;

    private ICategoryService categoryService;

    private ICourseService courseService;

    private IBillService billService;

    private ICarService carService;

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
        return roleService.findByName(value);
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
        return commentService.findById(id);
    }

    @StringToCategoryMapping
    public Category toCategory(String name) {
        return categoryService.findByName(name);
    }

    @StringToCarTypeMapping
    public CarType toCarType(String name) {
        if (name.equals(CarType.AUTO.getDisplayName()))
            return CarType.AUTO;
        else return CarType.MANUAL;
    }

    @CarTypeToStringMapping
    public String toString(CarType carType) {
        return carType.getDisplayName();
    }

    @StringToCourseMapping
    public Course toCourse(String title) {
        return courseService.findByTitle(title);
    }

    @StringToCarMapping
    public Car toCar(String name) {
        return carService.findByName(name);
    }
}
