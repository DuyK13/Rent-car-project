package com.iuh.rencar_project.dto.response;

import java.time.LocalDateTime;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 9:37 AM
 */
public class ReservationResponse {
    private Long id;
    private LocalDateTime pickupDate;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String state;

    public ReservationResponse(Long id, LocalDateTime pickupDate, String fullName, String phoneNumber, String email, String state) {
        this.id = id;
        this.pickupDate = pickupDate;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = state;
    }

    public ReservationResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
