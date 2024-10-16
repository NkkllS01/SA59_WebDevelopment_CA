package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.repository.ShippingRepository;
import sg.edu.nus.ophone.model.Shipping;

//Team3.Kuo Chi
@Service
@Transactional
public class ShippingService {
    @Autowired
    ShippingRepository shipRepo;

    @Transactional
    public Shipping createShipping(@Valid Order order,String address, String city, String postalCode) {
        Shipping shipping = new Shipping();
        shipping.setOrder(order);
        shipping.setAddress(address);
        shipping.setCity(city);
        shipping.setPostalCode(postalCode);
        shipping.setShippingStatus("Processing");
        return shipRepo.save(shipping);
    }

    @Transactional
    public boolean updateShippingStatus(int orderId, String status) {
        Shipping shipping = shipRepo.findByOrderId(orderId);
        if(shipping != null) {
            shipping.setShippingStatus(status);
            shipRepo.save(shipping);
            return true;
        }
        return false;
    }

}
