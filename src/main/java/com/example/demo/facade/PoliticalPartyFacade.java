package com.example.demo.facade;

import com.example.demo.domain.PoliticalParty;
import com.example.demo.facade.dto.PoliticalPartyDTO;
import com.example.demo.facade.mapper.PoliticalPartyMapper;
import com.example.demo.service.PoliticalPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PoliticalPartyFacade {
    private final Logger log = LoggerFactory.getLogger(PoliticalPartyFacade.class);

    private final PoliticalPartyMapper mapper;

    private final PoliticalPartyService service;


    public PoliticalPartyFacade(PoliticalPartyMapper mapper, PoliticalPartyService service) {
        this.mapper = mapper;
        this.service = service;
    }
    public PoliticalPartyDTO save(PoliticalPartyDTO politicalPartyDTO) {
        log.debug("Request to save PoliticalParty : {}", politicalPartyDTO);
        PoliticalParty politicalParty = mapper.toEntity(politicalPartyDTO);
        politicalParty = service.save(politicalParty);
        return mapper.toDto(politicalParty);
    }

    public Optional<PoliticalPartyDTO> partialUpdate(PoliticalPartyDTO politicalPartyDTO) {
        log.debug("Request to partially update PoliticalParty : {}", politicalPartyDTO);
        return service
                .findOne(politicalPartyDTO.getId())
                .map(
                        existingPoliticalParty -> {
                            mapper.partialUpdate(existingPoliticalParty, politicalPartyDTO);
                            return existingPoliticalParty;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<PoliticalPartyDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public PoliticalPartyDTO findOne(Long id) {
        log.debug("Request to get PoliticalParty : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete PoliticalParty : {}", id);
        service.delete(id);
    }
}
