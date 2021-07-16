package ch.zli.mm.athlete.controller;

import ch.zli.mm.athlete.entity.Athlete;
import ch.zli.mm.athlete.entity.User;
import ch.zli.mm.athlete.model.JwtAuthenticationUserModel;
import ch.zli.mm.athlete.service.DBManager;
import ch.zli.mm.athlete.service.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private DBManager DBMANAGER;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    // create a new user and save to DB
    // on POST, always send JSON including the User - as AuthenticationUserModel
    @PostMapping(value = "/registerUser", produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody JwtAuthenticationUserModel inputUser) {
        // return 409 ERROR if user already exists in DB (with same EMAIL)
        if (DBMANAGER.findUser(inputUser.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // if user doesn't exist => add to DB, encrypt password and return as http response OK 200
        User newUser = new User(inputUser.getEmail(), bcryptEncoder.encode(inputUser.getPassword()));
        DBMANAGER.saveUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    // authenticate user with given credentials in DB
    // on POST, always send JSON including the User - as AuthenticationUserModel
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationUserModel inputUser) {
        // when authentication fails, return with status code 401 UNAUTHORIZED
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inputUser.getEmail(), inputUser.getPassword()));
            System.out.println(DBMANAGER.findUser(inputUser.getEmail()).toString());
            System.out.println(jwtTokenUtil.generateToken(DBMANAGER.findUser(inputUser.getEmail()).getEmail()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // generate token with corresponding username in DB (prevents errors) and returns it with status OK 200
        return new ResponseEntity<>(jwtTokenUtil.generateToken(DBMANAGER.findUser(inputUser.getEmail()).getEmail()), HttpStatus.OK);
    }



    @GetMapping(value = "/isAuthenticated", produces = "application/json")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        if(jwtTokenUtil.validateToken(token.substring(7), DBMANAGER.findUser(jwtTokenUtil.getEmailFromToken(token.substring(7))))) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

}
