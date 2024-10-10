package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.User;

public interface UserService {

    boolean login(String username,String password);
    User findUserByUserId(int userId);
    User findUserByEmail(String email);
    User findByName(String name);
    void saveUser(User user);
}