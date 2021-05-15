package com.iuh.rencar_project.dto.response;

import java.util.Date;
import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 12:55 PM
 */
public class CategoryResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String status;
    private CategoryResponse category;
    private List<CarResponse> cars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public List<CarResponse> getCars() {
        return cars;
    }

    public void setCars(List<CarResponse> cars) {
        this.cars = cars;
    }

    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String name, String slug, String description, String createdBy, Date createdDate, String modifiedBy, String modifiedDate, String status, CategoryResponse category, List<CarResponse> cars) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
        this.category = category;
        this.cars = cars;
    }
}
