package ch.zli.mm.athlete.controller;

import ch.zli.mm.athlete.entity.Athlete;
import ch.zli.mm.athlete.entity.User;
import ch.zli.mm.athlete.service.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private DBManager DBMANAGER;


    @PostMapping("/login")
    public ResponseEntity<?> getAthletes(@RequestBody User user) {
        for (User u:DBMANAGER.getAllUsers()) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
