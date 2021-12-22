package com.example.demo.web.rest;

import com.example.demo.facade.AddressFacade;
import com.example.demo.facade.dto.AddressDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "address";

    private final AddressFacade facade;

    public AddressResource(AddressFacade facade) {
        this.facade = facade;
    }


    @PostMapping("/adresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid AddressDTO addressDTO) throws URISyntaxException {
        if (addressDTO.getId() != null) {
            throw new BadRequestAlertException("A new address cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressDTO result = facade.save(addressDTO);
        return ResponseEntity
                .created(new URI("/api/adresses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/adresses/{id}")
    public ResponseEntity<AddressDTO> partialUpdateAddress(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody AddressDTO addressDTO
    ) {
        if (addressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, addressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        Optional<AddressDTO> result = facade.partialUpdate(addressDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
    }


    @GetMapping("/adresses")
    public ResponseEntity<Page<AddressDTO>> getAllAddresses(Pageable pageable) {
        Page<AddressDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/adresses/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
        var addressDTO = facade.findOne(id);
        return ResponseEntity.ok().body(addressDTO);
    }

    //I did the delete method, because it was a study, but within the context it doesn't make sense and it wouldn't be called
    @DeleteMapping("/adresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
