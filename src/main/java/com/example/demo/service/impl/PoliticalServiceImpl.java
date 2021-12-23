package com.example.demo.service.impl;

import com.example.demo.domain.Political;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.PoliticalRepository;
import com.example.demo.service.PoliticalService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class PoliticalServiceImpl implements PoliticalService {

    private static final String ENTITY_NAME = "political";

    private final PoliticalRepository repository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;


    public PoliticalServiceImpl(PoliticalRepository repository, AddressRepository addressRepository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.phoneRepository = phoneRepository;
    }


    @Override
    public Political save(Political political) {
        return repository.save(political);
    }

    @Override
    public Page<Political> findAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable);
    }

    @Override
    public Optional<Political> findOne(@NotNull Long id) {
        return repository.findByDeletedFalseAndId(id);
    }

    @Override
    public void delete(Long id) {
        var political = repository.findById(id).orElseThrow();
        political.setDeleted(true);
        repository.save(political);
        addressRepository.deleteAll(political.getAddresses());
        phoneRepository.deleteAll(political.getPhones());

    }
}
