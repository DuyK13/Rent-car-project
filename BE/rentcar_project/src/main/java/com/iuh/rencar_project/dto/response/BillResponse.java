package com.iuh.rencar_project.dto.response;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:08 AM
 */
public class BillResponse {

    private Long id;
    private String fullname;
    private String slug;
    private String phoneNumber;
    private String email;
    private Date createdDate;
    private LocalDateTime timeStart;
    private Long extraTime;
    private List<CourseResponse> courses;
    private CarResponse car;
    private String state;

    public BillResponse(Long id, String fullname, String slug, String phoneNumber, String email, Date createdDate, LocalDateTime timeStart, Long extraTime, List<CourseResponse> courses, CarResponse car, String state) {
        this.id = id;
        this.fullname = fullname;
        this.slug = slug;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdDate = createdDate;
        this.timeStart = timeStart;
        this.extraTime = extraTime;
        this.courses = courses;
        this.car = car;
        this.state = state;
    }

    public BillResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public Long getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(Long extraTime) {
        this.extraTime = extraTime;
    }

    public List<CourseResponse> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResponse> courses) {
        this.courses = courses;
    }

    public CarResponse getCar() {
        return car;
    }

    public void setCar(CarResponse car) {
        this.car = car;
    }
}