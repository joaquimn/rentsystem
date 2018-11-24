package service;

import domain.User;
import exception.EntityNotFoundException;
import repository.Repository;
import repository.UserRepository;

import java.util.List;

public class UserService implements Service<User>{

    private Repository<User> userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " was not found!"));
    }

    public void remove(String id) {
        userRepository.remove(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id "+id+ " was not found")));
    }


    @Override
    public void add(User user) {

    }

    @Override
    public void modify(User user) {

    }




}
