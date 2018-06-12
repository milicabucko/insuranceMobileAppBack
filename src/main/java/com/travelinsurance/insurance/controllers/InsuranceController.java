package com.travelinsurance.insurance.controllers;

import com.travelinsurance.insurance.dtos.ApproveInsuranceDto;
import com.travelinsurance.insurance.models.Insurance;
import com.travelinsurance.insurance.models.MailSending;
import com.travelinsurance.insurance.models.User;
import com.travelinsurance.insurance.models.Vehicle;
import com.travelinsurance.insurance.services.InsuranceService;
import com.travelinsurance.insurance.services.UserService;
import com.travelinsurance.insurance.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private MailSending mailSending;

    @RequestMapping(
            value = "/insurance/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Insurance> createInsurance (@RequestBody Insurance insurance){
        User user = userService.findByUsername(insurance.getBuyer().getUsername());
        List<User> insuredUsers = new ArrayList<>();
        for(User u: insurance.getInsuredUsers()){
            User temp = new User(u.getName(), u.getSurname(), "INSURED");
            userService.save(temp);
            insuredUsers.add(temp);
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setMark(insurance.getVehicle().getMark());
        vehicle.setModel(insurance.getVehicle().getModel());
        vehicle.setPlates(insurance.getVehicle().getPlates());
        vehicle.setYearOfProduction(insurance.getVehicle().getYearOfProduction());
        vehicle.setOwner(insurance.getBuyer());
        vehicle = vehicleService.save(vehicle);
        Insurance i = new Insurance();
        i.setBuyer(user);
        i.setInsuredUsers(insuredUsers);
        i.setFromDate(insurance.getFromDate());
        i.setToDate(insurance.getToDate());
        i.setNumOfPeople(insurance.getNumOfPeople());
        i.setVehicle(vehicle);
        i.setApproved(false);
        i.setDestination(insurance.getDestination());
        i.setPrice(insurance.getPrice());
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
    public ResponseEntity<Boolean>  approveInsurance(@RequestBody ApproveInsuranceDto approveInsuranceDto){
        Optional<Insurance> insurance = insuranceService.getById(approveInsuranceDto.getId());
        if(insurance != null){
            String message = "Insurance is approved now. \n\n Insurance buyer: \n " +
                             "Name: "  + insurance.get().getBuyer().getName() + "\n" +
                             "Surname: " + insurance.get().getBuyer().getSurname() + "\n" +
                             "Email: " + insurance.get().getBuyer().getEmail() + "\n" +
                             "Information about insurance" + "\n" +
                             "Number of people: " + insurance.get().getNumOfPeople() + "\n" +
                             "Start date: " + insurance.get().getFromDate() + "\n" +
                             "End date: " + insurance.get().getToDate() + "\n" +
                             "Price: " + insurance.get().getPrice();
            String messageNot = "Unfortunately insurance is not approved.";
            insurance.get().setApproved(approveInsuranceDto.isApprove());
            insurance.get().setSalesman(approveInsuranceDto.getSeller());
            Insurance savedInsurance = insuranceService.save(insurance.get());

            if(approveInsuranceDto.isApprove()){
                mailSending.sendInsurance(savedInsurance, message);
            }
            else{
                mailSending.sendInsurance(savedInsurance, messageNot);
            }

            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/insurance/getAllInsurance/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Insurance>> findAllUsersInsurance (@PathVariable String username){

        Collection<Insurance> allUserInsurance = insuranceService.findByBuyer_Username(username);

        return new ResponseEntity<>(allUserInsurance, HttpStatus.OK);
    }


}
