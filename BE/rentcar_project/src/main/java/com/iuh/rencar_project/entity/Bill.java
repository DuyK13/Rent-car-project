package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.BillState;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bills", uniqueConstraints = {@UniqueConstraint(columnNames = "slug")})
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String slug;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private User staff;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm tt")
    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @Column(name = "extra_time")
    private Long extraTime;

    @ManyToMany
    @JoinTable(name = "bills_courses", joinColumns = {@JoinColumn(name = "bill_id")}, inverseJoinColumns = {
            @JoinColumn(name = "course_id")})
    private Set<Course> courses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillState state;

    public Bill() {
        super();
        this.state = BillState.Pre_Order;
    }

    public Bill(Long id, String fullname, String slug, String phoneNumber, String email, User staff, LocalDateTime createdDate, LocalDateTime timeStart, Long extraTime, Set<Course> courses, Car car, BillState state) {
        this.id = id;
        this.fullname = fullname;
        this.slug = slug;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.staff = staff;
        this.createdDate = createdDate;
        this.timeStart = timeStart;
        this.extraTime = extraTime;
        this.courses = courses;
        this.car = car;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(Long extraTime) {
        this.extraTime = extraTime;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public BillState getState() {
        return state;
    }

    public void setState(BillState state) {
        this.state = state;
    }
}
