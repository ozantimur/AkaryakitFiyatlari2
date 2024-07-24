package com.example.akaryakitfiyatlari2.model;

import java.util.List;

public class CityResponse {
    private List<String> cities;
    private List<String> districts;

    // Getters and setters
    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getDistricts() {
        return districts;
    }

    public void setDistricts(List<String> districts) {
        this.districts = districts;
    }
}

