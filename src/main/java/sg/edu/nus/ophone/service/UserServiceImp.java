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
        List<User> users=userRepository.findByName(name);

        if(!users.isEmpty()) {
            User user=users.get(0);
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User findUserByUserId(int userId) {
        return userRepository.findById(userId).get();
    }
}