package com.travelinsurance.insurance.repositories;

import com.travelinsurance.insurance.models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findAllByIsApproved(boolean b);
}
