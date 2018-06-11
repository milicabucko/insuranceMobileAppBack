package com.travelinsurance.insurance.controllers;

import com.travelinsurance.insurance.dtos.ApproveInsuranceDto;
import com.travelinsurance.insurance.models.Insurance;
import com.travelinsurance.insurance.models.User;
import com.travelinsurance.insurance.services.InsuranceService;
import com.travelinsurance.insurance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        i.setApproved(false);
        Insurance retValue = insuranceService.save(i);
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/insurance/getUnapproved",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Insurance>> getUnapproved (){
        List<Insurance> unApproved = insuranceService.getAllByIsApproved(false);
        if(unApproved != null){
                return new ResponseEntity<>(unApproved, HttpStatus.OK);

        }
        return new ResponseEntity<>((List<Insurance>) null, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/insurance/approve",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Insurance>  approveInsurance(@RequestBody ApproveInsuranceDto approveInsuranceDto){
        Optional<Insurance> insurance = insuranceService.getById(approveInsuranceDto.getId());
        if(insurance != null){
            insurance.get().setApproved(approveInsuranceDto.isApprove());
            insurance.get().setSalesman(approveInsuranceDto.getSeller());
            insuranceService.save(insurance.get());
            return new ResponseEntity<>(insurance.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>((Insurance) null, HttpStatus.OK);
    }
}
