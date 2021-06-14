package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.ReservationState;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 7:43 AM
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reservations", indexes = @Index(name = "IDX_Search", columnList = "full_name, phone_number, email"))
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private ReservationState state;

    public Reservation(Long id, LocalDateTime pickupDate, String fullName, String phoneNumber, String email, ReservationState state) {
        this.id = id;
        this.pickupDate = pickupDate;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = state;
    }

    public Reservation() {
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

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }
}
