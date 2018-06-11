package com.travelinsurance.insurance.repositories;

import com.travelinsurance.insurance.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
