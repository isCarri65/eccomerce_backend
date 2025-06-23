package com.ecommerce.services;

import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.dto.Address.CreateAddressDTO;
import com.ecommerce.dto.Address.UpdateAddressDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.mappers.AddressMapper;
import com.ecommerce.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService extends BaseService<Address, Long>{
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        super(addressRepository);
        this.addressMapper = addressMapper;
    }

    public Set<Address> getAllByUserId(Long userId){
        return  addressRepository.findAllByUser_Id(userId);
    }


    public AddressDTO updateWhitDTO (Long addressId, UpdateAddressDTO addressDTO, String email) throws AccessDeniedException {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("No se encontró la dirección con ID: " + addressId));
        if (!email.equals(address.getUser().getEmail())){
            throw new AccessDeniedException("no tienes acceso a esta direccion");
        }
        Address updatedAddress = addressRepository.save(addressMapper.UDTOtoEntity(addressDTO, addressId));
        return addressMapper.toDTO(updatedAddress);
    }

    public AddressDTO createWhitDTO (CreateAddressDTO addressDTO, Long userId) {
        addressDTO.setUserId(userId);
        Address address = addressMapper.CDTOtoEntity(addressDTO);
         Address createdAddress = addressRepository.save(address);
         return addressMapper.toDTO(createdAddress);
    }
}
