package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.BillState;
import com.iuh.rencar_project.utils.enums.BillType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private User createdBy;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "modified_by", referencedColumnName = "id")
    private User modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private BillType type;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm tt")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm tt")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillState state;

    @Column(name = "bill_amount")
    private Long billAmount;

    @Column(name = "late_charge")
    private Long lateCharge;

    public Bill(Long id, String fullname, String slug, String phoneNumber, String email, User createdBy, LocalDateTime createdDate, User modifiedBy, LocalDateTime modifiedDate, BillType type, LocalDateTime startTime, LocalDateTime endTime, Course course, Car car, BillState state, Long billAmount, Long lateCharge) {
        this.id = id;
        this.fullname = fullname;
        this.slug = slug;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
        this.car = car;
        this.state = state;
        this.billAmount = billAmount;
        this.lateCharge = lateCharge;
    }

    public Bill() {
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BillState getState() {
        return state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    public Long getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Long billAmount) {
        this.billAmount = billAmount;
    }

    public Long getLateCharge() {
        return lateCharge;
    }

    public void setLateCharge(Long lateCharge) {
        this.lateCharge = lateCharge;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", slug='" + slug + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", modifiedBy=" + modifiedBy +
                ", modifiedDate=" + modifiedDate +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", course=" + course +
                ", car=" + car +
                ", state=" + state +
                ", billAmount=" + billAmount +
                ", lateCharge=" + lateCharge +
                '}';
    }
}
