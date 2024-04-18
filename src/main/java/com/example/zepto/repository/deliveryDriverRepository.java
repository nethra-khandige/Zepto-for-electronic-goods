package com.example.zepto.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.zepto.model.deliveryDriver;

public interface deliveryDriverRepository extends JpaRepository<deliveryDriver,Long> {
	Optional<deliveryDriver> findTopByAvailable(boolean val);

}
