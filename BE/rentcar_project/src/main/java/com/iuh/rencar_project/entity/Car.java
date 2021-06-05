package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.CarState;
import com.iuh.rencar_project.utils.enums.Status;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "slug", "license_plate"})}, indexes = {@Index(name = "IDX_Search", columnList = "name, manufacturing_year, license_plate, created_date, modified_date")})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "manufacturing_year", nullable = false)
    private int manufacturingYear;

    @Column(name = "cost_per_hour", nullable = false)
    private int costPerHour;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarState state;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "license_plate")
    private String licensePlate;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
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

    public Car(Long id, String name, int manufacturingYear, int costPerHour, String slug, String image, int availableQuantity, CarState state, Status status, String licensePlate, User createdBy, LocalDateTime createdDate, User modifiedBy, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.manufacturingYear = manufacturingYear;
        this.costPerHour = costPerHour;
        this.slug = slug;
        this.image = image;
        this.state = state;
        this.status = status;
        this.licensePlate = licensePlate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public Car() {
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

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public int getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(int costPerHour) {
        this.costPerHour = costPerHour;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturingYear=" + manufacturingYear +
                ", costPerHour=" + costPerHour +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", state=" + state +
                ", status=" + status +
                ", licensePlate='" + licensePlate + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", modifiedBy=" + modifiedBy +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
