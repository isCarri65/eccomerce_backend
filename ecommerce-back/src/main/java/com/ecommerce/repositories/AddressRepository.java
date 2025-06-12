package com.ecommerce.repositories;

import com.ecommerce.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long>{

    Set<Address> findAllByUser_Id(Long id);
}
