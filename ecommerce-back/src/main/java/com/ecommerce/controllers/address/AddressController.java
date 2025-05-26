package com.ecommerce.controllers.address;

import com.ecommerce.controllers.BaseController;
import com.ecommerce.entities.Address;
import com.ecommerce.services.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseController<Address, Long> {
    public AddressController(AddressService addressService){
        super(addressService);
    }
}
