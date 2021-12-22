package com.example.demo.facade;

import com.example.demo.domain.State;
import com.example.demo.facade.dto.StateDTO;
import com.example.demo.facade.mapper.StateMapper;
import com.example.demo.service.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StateFacade {

    private final Logger log = LoggerFactory.getLogger(StateFacade.class);

    private final StateMapper mapper;

    private final StateService service;


    public StateFacade(StateMapper mapper, StateService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public StateDTO save(StateDTO stateDTO) {
        log.debug("Request to save State : {}", stateDTO);
        State state = mapper.toEntity(stateDTO);
        state = service.save(state);
        return mapper.toDto(state);
    }

    public Optional<StateDTO> partialUpdate(StateDTO stateDTO) {
        log.debug("Request to partially update State : {}", stateDTO);
        return service
                .findOne(stateDTO.getId())
                .map(
                        existingState -> {
                            mapper.partialUpdate(existingState, stateDTO);
                            return existingState;
                        }
                )
                .map(service::save)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<StateDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public StateDTO findOne(Long id) {
        log.debug("Request to get State : {}", id);
        return mapper.toDto(service.findOne(id).orElseThrow());
    }

    public void delete(Long id) {
        log.debug("Request to delete State : {}", id);
        service.delete(id);
    }
}
