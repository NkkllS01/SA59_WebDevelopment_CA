package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.ophone.interfacemethods.ShippingInterface;
import sg.edu.nus.ophone.repository.ShippingRepository;
import sg.edu.nus.ophone.model.Shipping;

//Team3.Kuo Chi
@Service
@Transactional
public class ShippingImplementation implements ShippingInterface {
    @Autowired
    ShippingRepository shipRepo;

    @Override
    @Transactional
    public boolean saveShipping(Shipping shipping) {
        if (shipRepo.save(shipping) != null)
            return true;
        else
            return false;
    }

    @Override
    @Transactional
    public void createShipping(Shipping shipping) {
        shipRepo.save(shipping);
    }
}
