package com.example.demo.service;

import com.example.demo.domain.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PhoneService {
    Phone save(Phone phone);

    Page<Phone> findAll(Pageable pageable);

    Optional<Phone> findOne(Long id);

    void delete(Long id);
}
