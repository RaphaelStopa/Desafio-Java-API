package com.example.demo.service.impl;

import com.example.demo.domain.Phone;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.service.PhoneService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    private static final String ENTITY_NAME = "phone";

    private final PhoneRepository repository;

    public PhoneServiceImpl(PhoneRepository repository) {
        this.repository = repository;
    }


    @Override
    public Phone save(Phone phone) {
        return repository.save(phone);
    }


    @Override
    public Page<Phone> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Phone> findOne(@NotNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
