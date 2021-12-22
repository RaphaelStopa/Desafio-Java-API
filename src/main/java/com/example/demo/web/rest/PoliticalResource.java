package com.example.demo.web.rest;

import com.example.demo.facade.PoliticalFacade;
import com.example.demo.facade.dto.PoliticalDTO;
import com.example.demo.facade.dto.PoliticalToUpdateDTO;
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
public class PoliticalResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "political";

    private final PoliticalFacade facade;

    public PoliticalResource(PoliticalFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/politicians")
    public ResponseEntity<PoliticalDTO> createPolitical(@RequestBody PoliticalDTO politicalDTO) throws URISyntaxException {
        if (politicalDTO.getId() != null) {
            throw new BadRequestAlertException("A new Political cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PoliticalDTO result = facade.save(politicalDTO);
        return ResponseEntity
                .created(new URI("/api/politicians/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/politicians/{id}")
    public ResponseEntity<PoliticalToUpdateDTO> partialUpdatePolitical(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody PoliticalDTO politicalDTO
    ) {
        if (politicalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, politicalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<PoliticalToUpdateDTO> result = facade.partialUpdate(politicalDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/politicians")
    public ResponseEntity<Page<PoliticalDTO>> getAllPoliticians(Pageable pageable) {
        Page<PoliticalDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/politicians/{id}")
    public ResponseEntity<PoliticalDTO> getPolitical(@PathVariable Long id) {
        var politicalDTO = facade.findOne(id);
        return ResponseEntity.ok().body(politicalDTO);
    }

    //I made a soft delete because there is no reason to delete a policy, also delete its processes and projects. In fact, the method of excluding in some entities and only because it was asked
    @DeleteMapping("/politicians/{id}")
    public ResponseEntity<Void> deletePolitical(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
