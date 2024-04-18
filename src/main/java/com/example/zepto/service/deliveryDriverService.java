package com.example.zepto.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zepto.model.deliveryDriver;
import com.example.zepto.repository.deliveryDriverRepository;

import jakarta.transaction.Transactional;

@Service
public class deliveryDriverService {
    @Autowired
    private deliveryDriverRepository deliveryDriverRepository;

    @Transactional // Ensures database updates are atomic
    public deliveryDriver assignDeliveryDriver() {
        Optional<deliveryDriver> availableDriver = deliveryDriverRepository.findTopByAvailable(true);
        if (availableDriver.isPresent()) {
            deliveryDriver driver = availableDriver.get();
            driver.setAvailable(false); // Mark driver unavailable after assignment
            deliveryDriverRepository.save(driver);
            return driver;
        } else {
            // Handle case where no driver is available (throw exception, notify user)
            throw new RuntimeException("No available delivery driver found.");
        }
    }

}
