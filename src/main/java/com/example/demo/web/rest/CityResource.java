package com.example.demo.web.rest;

import com.example.demo.facade.CityFacade;
import com.example.demo.facade.dto.CityDTO;
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
public class CityResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "city";

    private final CityFacade facade;

    public CityResource(CityFacade facade) {
        this.facade = facade;
    }


    @PostMapping("/cities")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDTO) throws URISyntaxException {
        if (cityDTO.getId() != null) {
            throw new BadRequestAlertException("A new City cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CityDTO result = facade.save(cityDTO);
        return ResponseEntity
                .created(new URI("/api/cities/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/cities/{id}")
    public ResponseEntity<CityDTO> partialUpdateCity(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody CityDTO cityDTO
    ) {
        if (cityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<CityDTO> result = facade.partialUpdate(cityDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/cities")
    public ResponseEntity<Page<CityDTO>> getAllCities(Pageable pageable) {
        Page<CityDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/cities/{id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Long id) {
        var cityDTO = facade.findOne(id);
        return ResponseEntity.ok().body(cityDTO);
    }

    //same thing as the other deletes so I did the same scheme. It makes no sense to delete a city.
    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
