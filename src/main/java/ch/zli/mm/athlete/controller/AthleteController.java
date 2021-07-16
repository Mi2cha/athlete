package ch.zli.mm.athlete.controller;

import ch.zli.mm.athlete.entity.Athlete;
import ch.zli.mm.athlete.service.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/athletes")
public class AthleteController {
    
    @Autowired
    private DBManager DBMANAGER;


    @GetMapping
    public ResponseEntity<?> getAthletes() {
        ArrayList<Athlete> dbAthletes = DBMANAGER.getAllAthletes();
        if (dbAthletes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dbAthletes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAthlete (@PathVariable(value = "id") int athleteId) {
        Athlete athlete = DBMANAGER.findAthlete(athleteId);
        if (athlete != null) {
            return new ResponseEntity<>(athlete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createAthlete(@RequestBody Athlete athlete) {
        System.out.println("hi");
        if (athlete != null) {
            DBMANAGER.saveAthlete(athlete);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAthlete (@RequestBody Athlete athlete, @PathVariable(value = "id") int athleteId) {

        Athlete dbAthlete = DBMANAGER.findAthlete(athleteId);

        if (dbAthlete!=null) {
            athlete.setId(athleteId);
            DBMANAGER.saveAthlete(athlete);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAthlete(@PathVariable(value = "id") int athleteId){
        Athlete dbAthlete = DBMANAGER.findAthlete(athleteId);
        if (dbAthlete != null){
            DBMANAGER.deleteAthlete(athleteId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}