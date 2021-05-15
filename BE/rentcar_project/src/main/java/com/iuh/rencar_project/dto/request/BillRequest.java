package com.iuh.rencar_project.dto.request;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:08 AM
 */
public class BillRequest {
    private String fullname;
    private String phoneNumber;
    private String email;
    private LocalDateTime timeStart;
    private Long extraTime;
    private List<String> courses;
    private String car;

    public BillRequest(String fullname, String phoneNumber, String email, LocalDateTime timeStart, Long extraTime, List<String> courses, String car) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.timeStart = timeStart;
        this.extraTime = extraTime;
        this.courses = courses;
        this.car = car;
    }

    public BillRequest() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}