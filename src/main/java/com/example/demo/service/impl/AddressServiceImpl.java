package com.example.demo.service.impl;

import com.example.demo.domain.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private static final String ENTITY_NAME = "address";

    private final AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Address> findOne(@NotNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
