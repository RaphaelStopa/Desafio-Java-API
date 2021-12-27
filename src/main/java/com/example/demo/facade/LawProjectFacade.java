package com.example.demo.facade;

import com.example.demo.domain.LawProject;
import com.example.demo.facade.dto.LawProjectDTO;
import com.example.demo.facade.mapper.LawProjectMapper;
import com.example.demo.service.LawProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LawProjectFacade {
    private final Logger log = LoggerFactory.getLogger(LawProjectFacade.class);

    private final LawProjectMapper mapper;

    private final LawProjectService service;


    public LawProjectFacade(LawProjectMapper mapper, LawProjectService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public LawProjectDTO save(LawProjectDTO lawProjectDTO) {
        log.debug("Request to save LawProject : {}", lawProjectDTO);
        LawProject lawProject = mapper.toEntity(lawProjectDTO);
        lawProject = service.save(lawProject);
        return mapper.toDto(lawProject);
    }

    public Optional<LawProjectDTO> partialUpdate(LawProjectDTO lawProjectDTO) {
        log.debug("Request to partially update LawProject : {}", lawProjectDTO);
        return service
                .findOne(lawProjectDTO.getId())
                .map(
                        existingLawProject -> {
                            mapper.partialUpdate(existingLawProject, lawProjectDTO);
                            return existingLawProject;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<LawProjectDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public LawProjectDTO findOne(Long id) {
        log.debug("Request to get LawProject : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete LawProject : {}", id);
        service.delete(id);
    }
}
