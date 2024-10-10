package sg.edu.nus.ophone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.UserService;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String name,String password) {
        User user=userRepository.findByName(name);

        if(user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User findUserByUserId(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}