package com.example.demo.facade;

import com.example.demo.domain.Phone;
import com.example.demo.facade.dto.PhoneDTO;
import com.example.demo.facade.mapper.PhoneMapper;
import com.example.demo.service.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PhoneFacade {

    private final Logger log = LoggerFactory.getLogger(PhoneFacade.class);

    private final PhoneMapper mapper;

    private final PhoneService service;

    public PhoneFacade(PhoneMapper mapper, PhoneService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public PhoneDTO save(PhoneDTO phoneDTO) {
        log.debug("Request to save Phone : {}", phoneDTO);
        Phone phone = mapper.toEntity(phoneDTO);
        phone = service.save(phone);
        return mapper.toDto(phone);
    }

    public Optional<PhoneDTO> partialUpdate(PhoneDTO phoneDTO) {
        log.debug("Request to partially update Phone : {}", phoneDTO);
        return service
                .findOne(phoneDTO.getId())
                .map(
                        existingPhone -> {
                            mapper.partialUpdate(existingPhone, phoneDTO);
                            return existingPhone;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<PhoneDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public PhoneDTO findOne(Long id) {
        log.debug("Request to get Phone : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete Phone : {}", id);
        service.delete(id);
    }
}
