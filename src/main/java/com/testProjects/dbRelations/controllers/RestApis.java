package com.testProjects.dbRelations.controllers;


import com.testProjects.dbRelations.exception.NotFoundException;
import com.testProjects.dbRelations.models.Mission;
import com.testProjects.dbRelations.models.User;
import com.testProjects.dbRelations.repository.MissionRepo;
import com.testProjects.dbRelations.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class RestApis {

    @GetMapping(value = "/test")
    public String apiTest() {
        return "tested ok !....";
    }


    /***********************************************************************/
    /************************* user controllers ****************************/
    /***********************************************************************/

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public List getAlluser() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public Object getUserByID(@PathVariable Long id) {
        Optional optUser = userRepo.findById(id);
        if(optUser.isPresent()) {
            return optUser.get();
        }else {
            throw new NotFoundException("Student not found with id " + id);
        }
    }

    @PostMapping("/user")
    public User createUser(@Validated @RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id,
                                 @Validated @RequestBody User user) {
        return userRepo.findById(id)
                .map(usr -> {
                    usr.setEmail(user.getEmail());
                    return userRepo.save(usr);
                }).orElseThrow(() -> new NotFoundException("Student not found with id " + id));
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(student -> {
                    userRepo.delete(student);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Student not found with id " + id));
    }





    /***********************************************************************/
    /************************* mission controllers ****************************/
    /***********************************************************************/
    @Autowired
    private MissionRepo missionRepo;

    //@Autowired
    //private UserRepo userRepo;

    @GetMapping("/user/{userId}/missions")
    public List getMissionByUserId(@PathVariable Long userId) {

        if(!userRepo.existsById(userId)) {
            throw new NotFoundException("User not found!");
        }

        return missionRepo.findAllByUserId(userId);
    }

    @PostMapping("/user/{userId}/missions")
    public Mission addMission(@PathVariable Long userId,
                                    @Validated @RequestBody Mission mission) {
        return userRepo.findById(userId)
                .map(usr -> {
                    mission.setUser(usr);
                    return missionRepo.save(mission);
                }).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @PutMapping("/user/{userId}/mission/{missionId}")
    public Mission updateMission(@PathVariable Long userId,
                                       @PathVariable Long missionId,
                                       @Validated @RequestBody Mission missionUpdated) {

        if(!userRepo.existsById(userId)) {
            throw new NotFoundException("User not found!");
        }

        return missionRepo.findById(missionId)
                .map(mission -> {
                    mission.setTitle(missionUpdated.getTitle());
                    mission.setDescription(missionUpdated.getDescription());
                    return missionRepo.save(mission);
                }).orElseThrow(() -> new NotFoundException("Mission not found!"));
    }

    @DeleteMapping("/user/{userId}/mission/{missionId}")
    public String deleteMission(@PathVariable Long userId,
                                   @PathVariable Long missionId) {

        if(!userRepo.existsById(userId)) {
            throw new NotFoundException("User not found!");
        }

        return missionRepo.findById(missionId)
                .map(mission -> {
                    missionRepo.delete(mission);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Mission not found!"));
    }


}
