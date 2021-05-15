package com.iuh.rencar_project.dto.request;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 12:57 PM
 */
public class CarRequest {
    private String name;
    private int year;
    private Long price;
    private String carType;
    private String image;

    public CarRequest(String name, int year, Long price, String carType, String image) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.carType = carType;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CarRequest() {
    }
}
