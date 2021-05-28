package com.iuh.rencar_project.dto.response;

import java.util.Date;

/**
 * The type Course response.
 *
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5 /15/2021 10:08 AM
 */
public class CourseResponse {
    private Long id;
    private String title;
    private Long price;
    private String slug;
    private Float timeCourse;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private String status;

    public CourseResponse() {
    }

    public CourseResponse(Long id, String title, Long price, String slug, Float timeCourse, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String status) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.slug = slug;
        this.timeCourse = timeCourse;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Float getTimeCourse() {
        return timeCourse;
    }

    public void setTimeCourse(Float timeCourse) {
        this.timeCourse = timeCourse;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
