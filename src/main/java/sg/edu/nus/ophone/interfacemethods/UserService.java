package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.User;

/**
 * 
 * Creating, Cancel, RetriveOrder(by userId or orderId), and comfirmOrder
 * order details in the cart system.
 * 
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */

public interface UserService {

	boolean login(String username,String password);
	
	 boolean usernameExists(String username);
	 
	void saveUser(User user);
}