package com.iuh.rencar_project.dto.response;

import java.util.Date;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 1:02 PM
 */
public class CarResponse {
    private Long id;
    private String name;
    private int year;
    private Long price;
    private String type;
    private String slug;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private String imageLink;
    private String categoryName;

    public CarResponse(Long id, String name, int year, Long price, String type, String slug, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String imageLink, String categoryName) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
        this.type = type;
        this.slug = slug;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.imageLink = imageLink;
        this.categoryName = categoryName;
    }

    public CarResponse() {
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
