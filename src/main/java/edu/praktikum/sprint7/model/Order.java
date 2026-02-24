package edu.praktikum.sprint7.model;

import java.util.List;

public class Order {
    private String firstName ;
    private String lastName ;
    private String address;
    private String metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> colors;



    public Order ( String firstName, String lastName, String address, String metroStation, String phone, String deliveryDate, Number rentTime, String comment, List<String> colors)
    {
        this.colors = colors;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colors = colors;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRentTime(Number rentTime) {
        this.rentTime = rentTime;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColors() {
        return colors;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getAddress() {
        return address;
    }


    public String getMetroStation() {
        return metroStation;
    }


    public String getPhone() {
        return phone;
    }


    public Number getRentTime() {
        return rentTime;
    }


    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }


    public void setColors(List<String> colors) {
        this.colors = colors;
    }

}

