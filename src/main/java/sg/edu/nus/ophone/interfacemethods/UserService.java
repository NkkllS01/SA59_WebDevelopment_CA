package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.User;

public interface UserService {

    boolean login(String username,String password);
    User findUserByUserId(int userId);
}