package com.iuh.rencar_project.dto.request;

import java.time.LocalDateTime;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 9:35 AM
 */
public class ReservationRequest {
    private LocalDateTime pickupDate;
    private String fullName;
    private String phoneNumber;
    private String email;

    public ReservationRequest(LocalDateTime pickupDate, String fullName, String phoneNumber, String email) {
        this.pickupDate = pickupDate;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ReservationRequest() {
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
}
