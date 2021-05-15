package com.iuh.rencar_project.dto.request;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 11:54 AM
 */
public class PostRequest {
    private String title;
    private String content;
    private List<String> tags;

    public PostRequest() {
    }

    public PostRequest(String title, String content, List<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
