package com.example.demo.web.rest;

import com.example.demo.facade.ProcessFacade;
import com.example.demo.facade.dto.ProcessDTO;
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
public class ProcessResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "process";

    private final ProcessFacade facade;

    public ProcessResource(ProcessFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/processes")
    public ResponseEntity<ProcessDTO> createProcess(@RequestBody ProcessDTO processDTO) throws URISyntaxException {
        if (processDTO.getId() != null) {
            throw new BadRequestAlertException("A new Process cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProcessDTO result = facade.save(processDTO);
        return ResponseEntity
                .created(new URI("/api/processes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/processes/{id}")
    public ResponseEntity<ProcessDTO> partialUpdateProcess(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody ProcessDTO processDTO
    ) {
        if (processDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, processDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<ProcessDTO> result = facade.partialUpdate(processDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/processes")
    public ResponseEntity<Page<ProcessDTO>> getAllProcesses(Pageable pageable) {
        Page<ProcessDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/processes/{id}")
    public ResponseEntity<ProcessDTO> getProcess(@PathVariable Long id) {
        var processDTO = facade.findOne(id);
        return ResponseEntity.ok().body(processDTO);
    }

    //I did the delete method, because it was a study, but within the context it doesn't make sense and it wouldn't be called
    @DeleteMapping("/processes/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
