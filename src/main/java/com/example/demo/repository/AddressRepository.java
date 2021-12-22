package com.example.demo.repository;

import com.example.demo.domain.Address;
import com.example.demo.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
