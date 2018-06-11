package com.travelinsurance.insurance.services;

import com.travelinsurance.insurance.models.Insurance;
import com.travelinsurance.insurance.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Insurance save(Insurance insurance) { return insuranceRepository.save(insurance); }


    public List<Insurance> getAllByIsApproved(boolean b) {
        return insuranceRepository.findAllByIsApproved(b);
    }

    public Optional<Insurance> getById(Long id){
        return insuranceRepository.findById(id);
    }

    public List<Insurance> findByBuyer_Username(String username) {
        return insuranceRepository.findByBuyer_Username(username);
    }
}
