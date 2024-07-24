package com.example.akaryakitfiyatlari2.controller;

import com.example.akaryakitfiyatlari2.model.CityResponse;
import com.example.akaryakitfiyatlari2.service.FuelLocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
public class FuelLocationController {

    private final FuelLocationService fuelLocationService;

    public FuelLocationController(FuelLocationService fuelLocationService) {
        this.fuelLocationService = fuelLocationService;
    }

    @GetMapping
    public CityResponse getCityLocations() {
        return fuelLocationService.getCityLocations();
    }
}
