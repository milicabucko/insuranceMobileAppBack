package com.travelinsurance.insurance.controllers;

import com.travelinsurance.insurance.models.Destination;
import com.travelinsurance.insurance.models.Insurance;
import com.travelinsurance.insurance.models.User;
import com.travelinsurance.insurance.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @RequestMapping(
            value = "/destination/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Destination> saveDestination (@RequestBody Destination destination){
        Destination d = destinationService.save(destination);
        if(d != null){
            return new ResponseEntity<>(d, HttpStatus.OK);
        }
        return new ResponseEntity<>((Destination) null, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/destination/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Destination>> getAllDestinations (){
        List<Destination> destinations = destinationService.getAll();
        if(destinations != null){
            return new ResponseEntity<>(destinations, HttpStatus.OK);

        }
        return new ResponseEntity<>((List<Destination>) null, HttpStatus.OK);
    }
}
