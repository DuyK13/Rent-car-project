package com.iuh.rencar_project.dto.response;

import java.util.Date;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/8/2021 12:42 PM
 */
public class CommentResponse {
    private Long id;
    private String name;
    private String email;
    private Date createdDate;
    private String content;
    private int likes;
    private int dislike;
    private String status;
    private CommentResponse comment;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String name, String email, Date createdDate, String content, int likes, int dislike, String status, CommentResponse comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
        this.content = content;
        this.likes = likes;
        this.dislike = dislike;
        this.status = status;
        this.comment = comment;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CommentResponse getComment() {
        return comment;
    }

    public void setComment(CommentResponse comment) {
        this.comment = comment;
    }
}
