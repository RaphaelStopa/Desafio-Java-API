package com.example.demo.facade;

import com.example.demo.domain.Political;
import com.example.demo.facade.dto.PoliticalDTO;
import com.example.demo.facade.dto.PoliticalForUserDTO;
import com.example.demo.facade.dto.PoliticalToUpdateDTO;
import com.example.demo.facade.mapper.PoliticalMapper;
import com.example.demo.service.PoliticalService;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PoliticalFacade {

    private final Logger log = LoggerFactory.getLogger(PoliticalFacade.class);

    private final PoliticalMapper mapper;

    private final PoliticalService service;

    public PoliticalFacade(PoliticalMapper mapper, PoliticalService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public PoliticalDTO save(PoliticalDTO politicalDTO) {
        log.debug("Request to save Political : {}", politicalDTO);
        Political political = mapper.toEntity(politicalDTO);
        political = service.save(political);
        return mapper.toDto(political);
    }

    public Optional<PoliticalToUpdateDTO> partialUpdate(PoliticalDTO politicalDTO) {
        log.debug("Request to partially update Political : {}", politicalDTO);
        return service
                .findOne(politicalDTO.getId())
                .map(
                        existingPolitical -> {
                            mapper.partialUpdate(existingPolitical, politicalDTO);
                            return existingPolitical;
                        }
                )
                .map(service::save)
                .map(mapper::toUpdateDto);
    }


    public Page<PoliticalForUserDTO> findAllForUser(Pageable pageable, Predicate predicate, Long numberOfLaws) {
        return service.findAllForUser(pageable, predicate, numberOfLaws).map(mapper::toUserDto);
    }


    @Transactional(readOnly = true)
    public Page<PoliticalDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public PoliticalDTO findOne(Long id) {
        log.debug("Request to get Political : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete Political : {}", id);
        service.delete(id);
    }
}
