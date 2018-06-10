package com.travelinsurance.insurance.controllers;

import com.travelinsurance.insurance.models.Insurance;
import com.travelinsurance.insurance.models.User;
import com.travelinsurance.insurance.services.InsuranceService;
import com.travelinsurance.insurance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/insurance/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Insurance> registration (@RequestBody Insurance insurance){
        User user = userService.findByUsername(insurance.getBuyer().getUsername());
        List<User> insuredUsers = new ArrayList<>();
        for(User u: insurance.getInsuredUsers()){
            User temp = new User(u.getName(), u.getSurname(), "INSURED");
            userService.save(temp);
            insuredUsers.add(temp);
        }
        Insurance i = new Insurance();
        i.setBuyer(user);
        i.setInsuredUsers(insuredUsers);
        i.setFromDate(insurance.getFromDate());
        i.setToDate(insurance.getToDate());
        i.setNumOfPeople(insurance.getNumOfPeople());
        Insurance retValue = insuranceService.save(i);
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }
}
