package com.example.madgroupproject;

public class order_data {
    String Name,Address,Phone,Card,Type,Status,DID,orderDetails,Price,date_time;

    public order_data() {
    }

    public order_data(String name, String address, String phone, String card, String type, String status, String DID,String orderDetails,String price,String DT) {
        Name = name;
        Address = address;
        Phone = phone;
        Card = card;
        Type = type;
        Status = status;
        this.DID = DID;
        this.orderDetails=orderDetails;
        Price=price;
        date_time=DT;



    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        this.DID = DID;
    }
}
