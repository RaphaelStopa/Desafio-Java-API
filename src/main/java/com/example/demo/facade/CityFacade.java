package com.example.demo.facade;

import com.example.demo.domain.City;
import com.example.demo.facade.dto.CityDTO;
import com.example.demo.facade.mapper.CityMapper;
import com.example.demo.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CityFacade {

    private final Logger log = LoggerFactory.getLogger(CityFacade.class);

    private final CityMapper mapper;

    private final CityService service;

    public CityFacade(CityMapper mapper, CityService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public CityDTO save(CityDTO cityDTO) {
        log.debug("Request to save City : {}", cityDTO);
        City city = mapper.toEntity(cityDTO);
        city = service.save(city);
        return mapper.toDto(city);
    }

    public Optional<CityDTO> partialUpdate(CityDTO cityDTO) {
        log.debug("Request to partially update City : {}", cityDTO);
        return service
                .findOne(cityDTO.getId())
                .map(
                        existingCity -> {
                            mapper.partialUpdate(existingCity, cityDTO);
                            return existingCity;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public CityDTO findOne(Long id) {
        log.debug("Request to get City : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        service.delete(id);
    }
}
