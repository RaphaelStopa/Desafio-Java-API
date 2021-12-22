package com.example.demo.web.rest;

import com.example.demo.facade.StateFacade;
import com.example.demo.facade.dto.StateDTO;
import com.example.demo.service.exceptions.BadRequestAlertException;
import com.example.demo.util.HeaderUtil;
import com.example.demo.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StateResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "state";

    private final StateFacade facade;

    public StateResource(StateFacade facade) {
        this.facade = facade;
    }


    @PostMapping("/states")
    public ResponseEntity<StateDTO> createState(@RequestBody StateDTO stateDTO) throws URISyntaxException {
        if (stateDTO.getId() != null) {
            throw new BadRequestAlertException("A new State cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StateDTO result = facade.save(stateDTO);
        return ResponseEntity
                .created(new URI("/api/states/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/states/{id}")
    public ResponseEntity<StateDTO> partialUpdateState(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody StateDTO stateDTO
    ) {
        if (stateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<StateDTO> result = facade.partialUpdate(stateDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/states")
    public ResponseEntity<Page<StateDTO>> getAllStates(Pageable pageable) {
        Page<StateDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/states/{id}")
    public ResponseEntity<StateDTO> getState(@PathVariable Long id) {
        var stateDTO = facade.findOne(id);
        return ResponseEntity.ok().body(stateDTO);
    }

    //it doesn't make sense to delete a state but I did as requested.
    @DeleteMapping("/states/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
