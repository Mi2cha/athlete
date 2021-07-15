package ch.zli.mm.athlete.service;

import ch.zli.mm.athlete.entity.User;
import ch.zli.mm.athlete.entity.Athlete;
import ch.zli.mm.athlete.entity.repository.UserRepository;
import ch.zli.mm.athlete.entity.repository.RoleRepository;
import ch.zli.mm.athlete.entity.repository.SportRepository;
import ch.zli.mm.athlete.entity.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DBManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private SportRepository sportRepository;

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public SportRepository getSportRepository() {
        return sportRepository;
    }

    public void setSportRepository(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    //Athlete

    /**
     * searches a Athlete object in the DB
     *
     * @param u is an object of the class Athlete
     * @return a Athlete object or null based on the data in the DB
     */
    public Athlete findAthlete(Athlete u) {
        Optional opt = athleteRepository.findById(u.getId());
        return opt.isPresent() ? (Athlete) opt.get() : null;
    }

    /**
     * searches a Athlete object in the DB with the id
     *
     * @param id is the id of a Athlete object
     * @return a Athlete object or null based on the data in the DB
     */
    public Athlete findAthlete(int id) {
        Optional opt = athleteRepository.findById(id);
        return opt.isPresent() ? (Athlete) opt.get() : null;
    }

    /**
     * searches a Athlete object in the DB with the name
     *
     * @param name is the name of a Athlete object
     * @return a Athlete object or null based on the data in the DB
     */
    public Athlete findAthlete(String name) {
        Optional opt = athleteRepository.findByName(name);
        return opt.isPresent() ? (Athlete) opt.get() : null;
    }

    /**
     * searches all the Athletes in the DB
     *
     * @return an ArrayList of Athlete objects Athlete object
     */
    public ArrayList<Athlete> getAllAthletes() {
        ArrayList<Athlete> Athletes = new ArrayList<>();
        for (Athlete u : athleteRepository.findAll()) {
            Athletes.add(u);
        }
        return Athletes;
    }

    /**
     * saves or updates a Athlete object in the db
     *
     * @param u is an object of the class Athlete which will be stores
     */
    public void saveAthlete(Athlete u) {
        athleteRepository.save(u);
    }

    /**
     * deletes a Athlete object in the db
     *
     * @param u is an object of the class Athlete which will be deleted (with all children)
     */
    public void deleteAthlete(Athlete u) {
        athleteRepository.delete(u);
    }

    /**
     * deletes a Athlete object in the db
     *
     * @param id is an id of a Athlete object which will be deleted (with all children)
     */
    public void deleteAthlete(int id) {
        athleteRepository.deleteById(id);
    }

    /**
     * searches a user object in the DB
     *
     * @param u is an object of the class User
     * @return a User object or null based on the data in the DB
     */
    public User findUser(User u) {
        Optional opt = userRepository.findById(u.getId());
        return opt.isPresent() ? (User) opt.get() : null;
    }

    /**
     * searches a user object in the DB with the id
     *
     * @param id is the id of a user object
     * @return a user object or null based on the data in the DB
     */
    public User findUser(int id) {
        Optional opt = userRepository.findById(id);
        return opt.isPresent() ? (User) opt.get() : null;
    }

    /**
     * searches a user object in the DB with the email
     *
     * @param email is the email of a user object
     * @return a user object or null based on the data in the DB
     */
    public User findUser(String email) {
        Optional opt = userRepository.findByEmail(email);
        return opt.isPresent() ? (User) opt.get() : null;
    }

    /**
     * searches all the users in the DB
     *
     * @return an ArrayList of User objects user object
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            users.add(u);
        }
        return users;
    }

    /**
     * saves or updates a user object in the db
     *
     * @param u is an object of the class user which will be stores
     */
    public void saveUser(User u) {
        userRepository.save(u);
    }

    /**
     * deletes a user object in the db
     *
     * @param u is an object of the class User which will be deleted (with all children)
     */
    public void deleteUser(User u) {
        userRepository.delete(u);
    }

    /**
     * deletes a user object in the db
     *
     * @param id is an id of a user object which will be deleted (with all children)
     */
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }




}
