package com.ecommerce.mappers;

import com.ecommerce.dto.Address.AddressAdminDTO;
import com.ecommerce.dto.Address.CreateAddressDTO;
import com.ecommerce.dto.Address.UpdateAddressDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.User;
import org.springframework.stereotype.Component;

@Component
public class AddressAdminMapper implements BaseAdminMapper<Address, Long, AddressAdminDTO, CreateAddressDTO, UpdateAddressDTO> {
    @Override
    public  AddressAdminDTO toDTO(Address address) {
        AddressAdminDTO dto = new AddressAdminDTO();
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
    public Address UDTOtoEntity (UpdateAddressDTO updateDTO, Long id){
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
        address.setDeleted(updateDTO.getDeleted());
        return address;
    }
    @Override
    public Address CDTOtoEntity(CreateAddressDTO addressDTO) {
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
