package com.example.madgroupproject;

public class DeliveryPersonModel {

    String name,contactNo,licenseNo,email;


    DeliveryPersonModel() {
    }

    public DeliveryPersonModel(String name, String contactNo, String licenseNo, String email) {
        this.name = name;
        this.contactNo = contactNo;
        this.licenseNo = licenseNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
