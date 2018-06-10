package com.travelinsurance.insurance.controllers;

import com.travelinsurance.insurance.dtos.PasswordDto;
import com.travelinsurance.insurance.models.User;
import com.travelinsurance.insurance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/user/save",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registration (@RequestBody User u){
        User user = userService.save(u);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/user/login/{username}/{password}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registration (@PathVariable String username, @PathVariable String password){
        User user = userService.findByUsername(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>((User) null, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/user/password",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updatePassword (@RequestBody PasswordDto passwordDto){
        User user = userService.findByUsernameAndPassword(passwordDto.getUsername(), passwordDto.getOldPassword());
        if(user != null){
            user.setPassword(passwordDto.getNewPassword());
            userService.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}
