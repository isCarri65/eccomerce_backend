package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.dto.Address.CreateAddressDTO;
import com.ecommerce.dto.Address.UpdateAddressDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.mappers.AddressMapper;
import com.ecommerce.services.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/addresses")
public class AddressController extends BaseController<Address, Long, AddressDTO, CreateAddressDTO, UpdateAddressDTO> {
    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        super(addressService, addressMapper);
    }
}
