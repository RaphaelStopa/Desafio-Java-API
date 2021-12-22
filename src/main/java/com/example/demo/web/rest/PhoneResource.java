package com.example.demo.web.rest;

import com.example.demo.facade.PhoneFacade;
import com.example.demo.facade.dto.PhoneDTO;
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
public class PhoneResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "phone";

    private final PhoneFacade facade;

    public PhoneResource(PhoneFacade facade) {
        this.facade = facade;
    }


    @PostMapping("/phones")
    public ResponseEntity<PhoneDTO> createPhone(@RequestBody PhoneDTO phoneDTO) throws URISyntaxException {
        if (phoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new Phone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhoneDTO result = facade.save(phoneDTO);
        return ResponseEntity
                .created(new URI("/api/phones/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/phones/{id}")
    public ResponseEntity<PhoneDTO> partialUpdatePhone(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody PhoneDTO phoneDTO
    ) {
        if (phoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, phoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<PhoneDTO> result = facade.partialUpdate(phoneDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/phones")
    public ResponseEntity<Page<PhoneDTO>> getAllPhones(Pageable pageable) {
        Page<PhoneDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/phones/{id}")
    public ResponseEntity<PhoneDTO> getPhone(@PathVariable Long id) {
        var phoneDTO = facade.findOne(id);
        return ResponseEntity.ok().body(phoneDTO);
    }

    //I did the delete method, because it was a study, but within the context it doesn't make sense and it wouldn't be called
    @DeleteMapping("/phones/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
