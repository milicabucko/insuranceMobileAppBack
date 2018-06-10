package com.travelinsurance.insurance.services;

import com.travelinsurance.insurance.models.Insurance;
import com.travelinsurance.insurance.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Insurance save(Insurance insurance) { return insuranceRepository.save(insurance); }


}
