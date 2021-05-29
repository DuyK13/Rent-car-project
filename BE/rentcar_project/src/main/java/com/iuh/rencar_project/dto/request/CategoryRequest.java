package com.iuh.rencar_project.dto.request;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 12:51 PM
 */
public class CategoryRequest {
    private String name;
    private String description;
    private String parent;

    public CategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
