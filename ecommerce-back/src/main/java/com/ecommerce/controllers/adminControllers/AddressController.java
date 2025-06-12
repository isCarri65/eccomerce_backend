package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.Address;
import com.ecommerce.services.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/addresses")
public class AddressController extends BaseController<Address, Long> {
    public AddressController(AddressService addressService){
        super(addressService);
    }
}
