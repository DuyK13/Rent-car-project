package com.iuh.rencar_project.dto.request;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:08 AM
 */
public class CourseRequest {
    private String title;
    private Long price;
    private Long timeCourse;

    public CourseRequest(String title, Long price, Long timeCourse) {
        this.title = title;
        this.price = price;
        this.timeCourse = timeCourse;
    }

    public CourseRequest() {
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

    public Long getTimeCourse() {
        return timeCourse;
    }

    public void setTimeCourse(Long timeCourse) {
        this.timeCourse = timeCourse;
    }
}
