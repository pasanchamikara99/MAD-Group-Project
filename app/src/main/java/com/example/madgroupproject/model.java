package com.example.madgroupproject;

public class model {

    String name,description,price,id,purl;

    model()
    {

    }

    public model(String name,String description,String price,String id,String purl)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.purl = purl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
