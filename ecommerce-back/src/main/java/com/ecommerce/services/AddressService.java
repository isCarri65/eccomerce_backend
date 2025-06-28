package com.ecommerce.services;

import com.ecommerce.entities.Address;
import com.ecommerce.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService extends BaseService<Address, Long>{
    private final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
        this.addressRepository = addressRepository;
    }

    public List<Address> findAddresForUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

}
