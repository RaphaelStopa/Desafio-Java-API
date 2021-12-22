package com.example.demo.repository;


import com.example.demo.domain.Phone;
import com.example.demo.domain.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
