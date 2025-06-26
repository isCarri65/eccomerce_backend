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
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService extends BaseService<Address, Long>{
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        super(addressRepository);
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllByUserId(Long userId){
        return  addressRepository.findAllByUser_Id(userId);
    }


    public AddressDTO updateWhitDTO (Long addressId, UpdateAddressDTO addressDTO, Long userId) throws AccessDeniedException {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("No se encontró la dirección con ID: " + addressId));
        if (!userId.equals(address.getUser().getId())){
            throw new AccessDeniedException("no tienes acceso a esta direccion");
        }
        addressDTO.setUserId(userId);
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
