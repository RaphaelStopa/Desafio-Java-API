package com.example.demo.web.rest;

import com.example.demo.domain.Political;
import com.example.demo.facade.PoliticalFacade;
import com.example.demo.facade.dto.PoliticalDTO;
import com.example.demo.facade.dto.PoliticalForUserDTO;
import com.example.demo.facade.dto.PoliticalToUpdateDTO;
import com.example.demo.service.exceptions.BadRequestAlertException;
import com.example.demo.util.HeaderUtil;
import com.example.demo.util.PaginationUtil;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Page<PoliticalDTO>> getAllPoliticiansForAdmin(Pageable pageable) {
        Page<PoliticalDTO> page = facade.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    // TODO esta errtado esto aqui, ajeitar no readme tambem
    ///api/{cargos}/asc Listar os Candidatos em ordem alfabética crescente por nome
    ////politicians/users?electivePositionType=CARGO&sort=name,asc
    ///api/{cargos}/desc Listar os Candidato em ordem alfabética decrescente por nome
    ////politicians/users?electivePositionType=CARGO&sort=name,desc
    ///api/{cargos}/{id} Buscar Candidato por id
    ///politicians/users?electivePositionType=CARGO&id={id}
    ///api/{cargos}/leis/{qnt}Filtrar pelo numero de Projetos de leis que o candidato trabalhou
    ///politicians/users/{numberOfLaws}?electivePositionType=CARGO

    @GetMapping(value = {"/politicians/users/{numberOfLaws}", "/politicians/users"})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<PoliticalForUserDTO>> getAllPoliticians(@PageableDefault(size = 2000, sort = "name") Pageable pageable, @QuerydslPredicate(root = Political.class) Predicate predicate, @PathVariable(value = "numberOfLaws", required = false) final Long numberOfLaws) {
        Page<PoliticalForUserDTO> page = facade.findAllForUser(pageable, predicate, numberOfLaws);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/politicians/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<PoliticalDTO> getPolitical(@PathVariable Long id) {
        var politicalDTO = facade.findOne(id);
        return ResponseEntity.ok().body(politicalDTO);
    }

    //I made a soft delete because there is no reason to delete a policy, also delete its processes and projects. In fact, the method of excluding in some entities and only because it was asked
    @DeleteMapping("/politicians/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePolitical(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
