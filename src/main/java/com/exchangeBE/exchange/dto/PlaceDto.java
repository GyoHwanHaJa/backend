package com.exchangeBE.exchange.dto;

public class PlaceDto {
    private String name;
    private String city;
    private String type;

    public PlaceDto(String name, String city, String type) {
        this.name = name;
        this.city = city;
        this.type = type;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
