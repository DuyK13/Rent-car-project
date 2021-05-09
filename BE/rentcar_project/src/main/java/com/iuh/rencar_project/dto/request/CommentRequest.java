package com.iuh.rencar_project.dto.request;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 12:38 PM
 */
public class CommentRequest {
    private String name;
    private String email;
    private String content;
    private Long commentId;

    public CommentRequest(String name, String email, String content, Long commentId) {
        this.name = name;
        this.email = email;
        this.content = content;
        this.commentId = commentId;
    }

    public CommentRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
