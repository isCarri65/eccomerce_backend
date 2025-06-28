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
    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
    }

    public Set<Address> getAllByUserId(Long userId){
        return  addressRepository.findAllByUser_Id(userId);
    }
    public AddressDTO updateWhitDTO (Long id, UpdateAddressDTO addressDTO, String email) throws AccessDeniedException {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        Address address = optionalAddress.orElseThrow(() -> new EntityNotFoundException("No se encontró la dirección con ID: " + id));
        if (!email.equals(address.getUser().getEmail())){
            throw new AccessDeniedException("no tienes acceso a esta direccion");
        }
        AddressMapper.updateDTOtoAddress(addressDTO, address);
        return AddressMapper.toDTO(address);
    }

    public AddressDTO createWhitDTO (CreateAddressDTO addressDTO, Long userId) {
        addressDTO.setUserId(userId);
        Address createAddress = AddressMapper.createDTOtoAddress(addressDTO);
         Address createdAddress = addressRepository.save(createAddress);
         return AddressMapper.toDTO(createdAddress);
    }
}
