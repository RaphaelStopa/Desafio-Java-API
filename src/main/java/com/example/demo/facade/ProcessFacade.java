package com.example.demo.facade;

import com.example.demo.domain.Process;
import com.example.demo.facade.dto.ProcessDTO;
import com.example.demo.facade.mapper.ProcessMapper;
import com.example.demo.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProcessFacade {

    private final Logger log = LoggerFactory.getLogger(ProcessFacade.class);

    private final ProcessMapper mapper;

    private final ProcessService service;

    public ProcessFacade(ProcessMapper mapper, ProcessService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public ProcessDTO save(ProcessDTO processDTO) {
        log.debug("Request to save Process : {}", processDTO);
        Process process = mapper.toEntity(processDTO);
        process = service.save(process);
        return mapper.toDto(process);
    }

    public Optional<ProcessDTO> partialUpdate(ProcessDTO processDTO) {
        log.debug("Request to partially update Process : {}", processDTO);
        return service
                .findOne(processDTO.getId())
                .map(
                        existingProcess -> {
                            mapper.partialUpdate(existingProcess, processDTO);
                            return existingProcess;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ProcessDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public ProcessDTO findOne(Long id) {
        log.debug("Request to get Process : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete Process : {}", id);
        service.delete(id);
    }
}
