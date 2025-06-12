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

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/protected/addresses")
public class AddressProtectedController {

    private final AddressService addressService;
    private final UserService userService;

    public AddressProtectedController(AddressService service, UserService userService) {
        this.addressService = service;
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<Set<AddressDTO>> getAll() {
        User user = userService.getCurrentUser();
        Set<Address> addresses = addressService.getAllByUserId(user.getId());
        Set<AddressDTO> addressesDTO = addresses.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(addressesDTO);
    }

    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<AddressDTO> updateMyAddress(@RequestBody UpdateAddressDTO addressDTO, @PathVariable Long id) throws Exception {
        String email = userService.getCurrentEmail();
        return ResponseEntity.ok(addressService.updateWhitDTO(id, addressDTO, email));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody CreateAddressDTO createAddressDTO) throws Exception {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(addressService.createWhitDTO(createAddressDTO, user.getId()));
    }





}
