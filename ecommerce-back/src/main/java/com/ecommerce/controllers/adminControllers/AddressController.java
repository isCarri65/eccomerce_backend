package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Address.*;
import com.ecommerce.entities.Address;
import com.ecommerce.mappers.AddressAdminMapper;
import com.ecommerce.mappers.AddressMapper;
import com.ecommerce.services.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/addresses")
public class AddressController extends BaseController<Address, Long, AddressAdminDTO, CreateAddressAdminDTO, UpdateAddressAdminDTO> {
    public AddressController(AddressService addressService, AddressAdminMapper addressMapper) {
        super(addressService, addressMapper);
    }
}
