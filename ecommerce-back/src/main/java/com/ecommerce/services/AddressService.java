package com.ecommerce.services;

import com.ecommerce.entities.Address;
import com.ecommerce.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseService<Address, Long>{

    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
    }
}
