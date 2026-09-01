package com.example.bookmyscreenbackend.dto;

import jakarta.validation.constraints.NotBlank;

// DTO used to receive and validate theater data from the client.
public class TheaterRequest {

    // Theater name.
    @NotBlank(message = "Name is required")
    private String name;

    // Theater location.
    @NotBlank(message = "Location is required")
    private String location;

    // Theater logo URL.
    @NotBlank(message = "Logo is required")
    private String logo;

    // City where the theater is located.
    @NotBlank(message = "City is required")
    private String city;

    // State where the theater is located.
    @NotBlank(message = "State is required")
    private String state;


    // Get and set theater name.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Get and set theater location.
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // Get and set theater logo.
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    // Get and set theater city.
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    // Get and set theater state.
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}