package com.ecommerce.mappers;

import com.ecommerce.dto.Address.*;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.User;
import org.springframework.stereotype.Component;

@Component
public class AddressAdminMapper implements BaseAdminMapper<Address, Long, AddressAdminDTO, CreateAddressAdminDTO, UpdateAddressAdminDTO> {
    @Override
    public  AddressAdminDTO toDTO(Address address) {
        AddressAdminDTO dto = new AddressAdminDTO();
        dto.setId(address.getId());
        dto.setDeleted(address.isDeleted());
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
    @Override
    public Address UDTOtoEntity (UpdateAddressAdminDTO updateDTO, Long id){
        Address address = new Address();
        User user = new User();
        user.setId(updateDTO.getUserId());
        address.setUser(user);
        address.setId(id);
        address.setProvince(updateDTO.getProvince());
        address.setLocality(updateDTO.getLocality());
        address.setNumber(updateDTO.getNumber());
        address.setStreet(updateDTO.getStreet());
        address.setPostal(updateDTO.getPostal());
        address.setAptNumberAndFloor(updateDTO.getAptNumberAndFloor());
        address.setApartment(updateDTO.getApartment());
        address.setDeleted(updateDTO.isDeleted());
        return address;
    }
    @Override
    public Address CDTOtoEntity(CreateAddressAdminDTO addressDTO) {
        Address address = new Address();
        User user = new User();
        user.setId(addressDTO.getUserId());
        address.setApartment(addressDTO.getApartment());
        address.setLocality(addressDTO.getLocality());
        address.setNumber(addressDTO.getNumber());
        address.setStreet(addressDTO.getStreet());
        address.setPostal(addressDTO.getPostal());
        address.setProvince(addressDTO.getProvince());
        address.setAptNumberAndFloor(addressDTO.getAptNumberAndFloor());
        address.setUser(user);
        return address;
    }
}
