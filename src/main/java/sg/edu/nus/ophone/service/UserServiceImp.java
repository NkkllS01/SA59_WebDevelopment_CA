package sg.edu.nus.ophone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.UserService;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.repository.UsersRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UsersRepository userRepository;

    public boolean login(String name,String password) {
        List<User> users=userRepository.findByName(name);

        if(!users.isEmpty()) {
            User user=users.get(0);
            return user.getPassword().equals(password);
        }
        return false;
    }
}