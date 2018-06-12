package com.travelinsurance.insurance.services;

import com.travelinsurance.insurance.models.Destination;
import com.travelinsurance.insurance.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Destination save(Destination destination){
        return destinationRepository.save(destination);
    }

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }
}
