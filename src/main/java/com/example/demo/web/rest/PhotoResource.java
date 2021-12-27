package com.example.demo.web.rest;

import com.example.demo.facade.PhotoFacade;
import com.example.demo.facade.dto.PhotoDTO;
import com.example.demo.service.exceptions.BadRequestAlertException;
import com.example.demo.util.HeaderUtil;
import com.example.demo.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class PhotoResource {

    @Value("${properties.clientApp.name}")
    private String applicationName;

    private static final String ENTITY_NAME = "photo";

    private final PhotoFacade facade;

    public PhotoResource(PhotoFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/photos")
    public ResponseEntity<PhotoDTO> upload(@RequestPart("politicalId") String politicalId, @RequestPart MultipartFile photo) throws Exception {
        if (politicalId == null) {
            throw new BadRequestAlertException("Every photo must have an owner", ENTITY_NAME, "non-existent politician");
        }
        PhotoDTO result = facade.save(politicalId, photo);

        return ResponseEntity
                .created(new URI("/api/photos/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping("/photos")
    public ResponseEntity<Page<PhotoDTO>> getAllPhones(Pageable pageable) {
        Page<PhotoDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    // It makes no sense to update a photo, but CRUDS was requested, I did it here to update the political
    @PatchMapping(value = "/photos/{id}")
    public ResponseEntity<PhotoDTO> partialUpdatePhone(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody PhotoDTO photoDTO
    ) {
        if (photoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, photoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        PhotoDTO result = facade.partialUpdate(photoDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result);
    }


    //aqui fazer voltar a base 64
    @GetMapping("/photos/{id}")
    public ResponseEntity<byte[]> getPhone(@PathVariable Long id) throws IOException {
        var photoDTO = facade.findOne(id);
        return ResponseEntity.ok().body(photoDTO);
    }


    @DeleteMapping("/photos/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }


}
