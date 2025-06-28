package com.ecommerce.mappers;

import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.dto.Address.CreateAddressDTO;
import com.ecommerce.dto.Address.UpdateAddressDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.User;

public class AddressMapper {
    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setApartment(address.getApartment());
        dto.setLocality(address.getLocality());
        dto.setNumber(address.getNumber());
        dto.setStreet(address.getStreet());
        dto.setPostal(address.getPostal());
        dto.setProvince(address.getProvince());
        dto.setUserId(address.getUser().getId());
        dto.setAptNumberAndFloor(address.getAptNumberAndFloor());
        return dto;
    }

    public static void updateDTOtoAddress (UpdateAddressDTO updateDTO, Address address){
        address.setProvince(updateDTO.getProvince());
        address.setLocality(updateDTO.getLocality());
        address.setNumber(updateDTO.getNumber());
        address.setStreet(updateDTO.getStreet());
        address.setPostal(updateDTO.getPostal());
        address.setAptNumberAndFloor(updateDTO.getAptNumberAndFloor());
        address.setApartment(updateDTO.getApartment());
    }

    public static Address createDTOtoAddress(CreateAddressDTO addressDTO) {
        Address address = new Address();
        User user = new User();
        user.setId(addressDTO.getUserId());
        address.setApartment(addressDTO.getApartment());
        address.setLocality(addressDTO.getLocality());
        address.setNumber(addressDTO.getNumber());
        address.setStreet(addressDTO.getStreet());
        address.setPostal(addressDTO.getPostal());
        address.setProvince(addressDTO.getProvince());
        address.setUser(user);
        return address;
    }
}
