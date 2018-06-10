package com.travelinsurance.insurance.repositories;

import com.travelinsurance.insurance.models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
