package com.travelinsurance.insurance.repositories;

import com.travelinsurance.insurance.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
