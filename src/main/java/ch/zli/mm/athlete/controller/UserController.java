package ch.zli.mm.athlete.controller;

import ch.zli.mm.athlete.entity.User;
import ch.zli.mm.athlete.entity.repository.UserRepository;
import ch.zli.mm.athlete.service.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private DBManager DBMANAGER;


    @GetMapping
    public ResponseEntity<?> getUsers() {
        ArrayList<User> dbUsers = DBMANAGER.getAllUsers();
        if (dbUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dbUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser (@PathVariable(value = "id") int userId) {
        User user = DBMANAGER.findUser(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println("hi");
        if (user != null) {
            DBMANAGER.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser (@RequestBody User user, @PathVariable(value = "id") int userId) {

       User dbUser = DBMANAGER.findUser(userId);

       if (dbUser!=null) {
           user.setId(userId);
            DBMANAGER.saveUser(user);
           return new ResponseEntity<>(HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId){
        User dbUser = DBMANAGER.findUser(userId);
        if (dbUser != null){
            DBMANAGER.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
