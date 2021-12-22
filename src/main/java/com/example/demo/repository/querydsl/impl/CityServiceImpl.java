package com.example.demo.repository.querydsl.impl;

import com.example.demo.domain.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;
import com.example.demo.service.exceptions.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private static final String ENTITY_NAME = "city";

    private final CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }


    @Override
    public City save(City city) {
        return repository.save(city);
    }

    @Override
    public Optional<City> partialUpdate(City city) {
        if (!repository.existsById(city.getId())) {
            throw BusinessException.badRequest();
        }
        return Optional.of(repository.save(city));
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<City> findOne(@NotNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
