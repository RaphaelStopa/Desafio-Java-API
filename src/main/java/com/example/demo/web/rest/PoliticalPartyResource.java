package com.example.demo.web.rest;

import com.example.demo.facade.PoliticalPartyFacade;
import com.example.demo.facade.dto.PoliticalPartyDTO;
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
public class PoliticalPartyResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "politicalParty";

    private final PoliticalPartyFacade facade;

    public PoliticalPartyResource(PoliticalPartyFacade facade) {
        this.facade = facade;
    }


    @PostMapping("/political-parties")
    public ResponseEntity<PoliticalPartyDTO> createPoliticalParty(@RequestBody PoliticalPartyDTO politicalPartyDTO) throws URISyntaxException {
        if (politicalPartyDTO.getId() != null) {
            throw new BadRequestAlertException("A new PoliticalParty cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PoliticalPartyDTO result = facade.save(politicalPartyDTO);
        return ResponseEntity
                .created(new URI("/api/political-parties/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/political-parties/{id}")
    public ResponseEntity<PoliticalPartyDTO> partialUpdatePoliticalParty(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody PoliticalPartyDTO politicalPartyDTO
    ) {
        if (politicalPartyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, politicalPartyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<PoliticalPartyDTO> result = facade.partialUpdate(politicalPartyDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/political-parties")
    public ResponseEntity<Page<PoliticalPartyDTO>> getAllPoliticalParties(Pageable pageable) {
        Page<PoliticalPartyDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/political-parties/{id}")
    public ResponseEntity<PoliticalPartyDTO> getPoliticalParty(@PathVariable Long id) {
        var politicalPartyDTO = facade.findOne(id);
        return ResponseEntity.ok().body(politicalPartyDTO);
    }

    //it makes no sense to exclude a party but I did as requested.
    @DeleteMapping("/political-parties/{id}")
    public ResponseEntity<Void> deletePoliticalParty(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
