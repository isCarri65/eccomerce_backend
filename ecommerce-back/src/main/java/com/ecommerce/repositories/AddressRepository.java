package com.ecommerce.repositories;

import com.ecommerce.entities.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long>{
    List<Address> findByUserId(Long userId);
}
