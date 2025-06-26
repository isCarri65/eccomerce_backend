package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.dto.Address.CreateAddressDTO;
import com.ecommerce.dto.Address.UpdateAddressDTO;
import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.AddressMapper;
import com.ecommerce.services.AddressService;
import com.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profile/addresses")
public class AddressProtectedController {

    private final AddressService addressService;
    private final UserService userService;
    private final AddressMapper addressMapper;

    public AddressProtectedController(AddressService service, UserService userService, AddressMapper addressMapper) {
        this.addressService = service;
        this.userService = userService;
        this.addressMapper = addressMapper;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<AddressDTO>> getAll() {
        User user = userService.getCurrentUser();
        List<Address> addresses = addressService.getAllByUserId(user.getId());
        List<AddressDTO> addressesDTO = addresses.stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(addressesDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressDTO> updateMyAddress(@RequestBody UpdateAddressDTO addressDTO, @PathVariable Long id) throws Exception {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(addressService.updateWhitDTO(id, addressDTO, user.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody CreateAddressDTO createAddressDTO) throws Exception {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(addressService.createWhitDTO(createAddressDTO, user.getId()));
    }





}
