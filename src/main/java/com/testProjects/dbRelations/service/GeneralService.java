package com.testProjects.dbRelations.service;

import com.testProjects.dbRelations.models.User;
import com.testProjects.dbRelations.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User>getAll() {
        return userRepo.findAll();
    }

    public Optional<User> findOne(Long id) {
        return userRepo.findById(id);
    }

    public User editUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    public String deleteUser(Long id) {
        userRepo.deleteById(id);
        return "User with " + id +" has been deleted successfully";
    }
}
