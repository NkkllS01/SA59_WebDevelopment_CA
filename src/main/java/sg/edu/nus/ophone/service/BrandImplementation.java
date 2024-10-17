package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.BrandInterface;
import sg.edu.nus.ophone.model.Brand;
import sg.edu.nus.ophone.repository.BrandRepository;

import java.util.List;

@Service
@Transactional
public class BrandImplementation implements BrandInterface {
    @Autowired
    private BrandRepository brandRepo;

    @Override
    public List<Brand> findAllBrands() {
        return brandRepo.findAll();
    }
}
