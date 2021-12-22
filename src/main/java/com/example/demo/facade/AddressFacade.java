package com.example.demo.facade;

import com.example.demo.domain.Address;
import com.example.demo.facade.dto.AddressDTO;
import com.example.demo.facade.mapper.AddressMapper;
import com.example.demo.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AddressFacade {

    private final Logger log = LoggerFactory.getLogger(AddressFacade.class);

    private final AddressMapper mapper;

    private final AddressService service;

    public AddressFacade(AddressMapper mapper, AddressService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address: {}", addressDTO);
        Address address = mapper.toEntity(addressDTO);
        address = service.save(address);
        return mapper.toDto(address);
    }

    public Optional<AddressDTO> partialUpdate(AddressDTO addressDTO) {
        log.debug("Request to partially update Address : {}", addressDTO);
        return service
                .findOne(addressDTO.getId())
                .map(
                        existingAddress -> {
                            mapper.partialUpdate(existingAddress, addressDTO);
                            return existingAddress;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<AddressDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public AddressDTO findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        service.delete(id);
    }
}
