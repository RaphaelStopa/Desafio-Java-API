package com.example.demo.web.rest;
import com.example.demo.domain.User;
import com.example.demo.facade.dto.PasswordChangeDTO;

import com.example.demo.repository.querydsl.impl.UserQueryRepositoryImpl;
import com.example.demo.service.exceptions.BusinessException;
import com.example.demo.service.exceptions.enumeration.ErrorConstants;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.PaginationUtil;
import com.example.demo.web.rest.vm.ManagedUserVM;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountResource {


    private final UserServiceImpl userServiceImpl;

    // o certo e nao chamar assim e sim a intercace com o metod
    //mas este eh so um exemplo
    private final UserQueryRepositoryImpl userQueryRepository;

    public AccountResource(UserServiceImpl userServiceImpl, UserQueryRepositoryImpl userQueryRepository) {
        this.userServiceImpl = userServiceImpl;
        this.userQueryRepository = userQueryRepository;
    }

    //TODO roles as they are are not working either
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAnyRole('ROLE_USER')")
    //@Secured({ AuthoritiesConstants.ADMIN })
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM){
        if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw BusinessException.by(ErrorConstants.INVALID_PASSWORD).get();
        }
        User user = userServiceImpl.registerUser(managedUserVM, managedUserVM.getPassword());
        //mailService.sendActivationEmail(user);
    }

    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (isPasswordLengthInvalid(passwordChangeDto.getNewPassword())) {
            throw BusinessException.by(ErrorConstants.INVALID_PASSWORD).get();
        }
        userServiceImpl.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }


    private static boolean isPasswordLengthInvalid(String password) {
        return (
                StringUtils.isEmpty(password) ||
                        password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
                        password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }


    //so como exemplo
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllPlanets(Pageable pageable, Predicate predicate) {
        var nha = userQueryRepository.findAllExemple(pageable, predicate);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), nha);
        return ResponseEntity.ok().headers(headers).body(nha);
    }


//    @Value("${properties.clientApp.name}")
//    private String applicationName;
//
//    private static final String ENTITY_NAME = "planet";
//
//    private final PlanetFacade facade;
//
//    public PlanetResource(PlanetFacade facade) {
//        this.facade = facade;
//    }
//
//
//    @PostMapping("/planets")
//    public ResponseEntity<PlanetDTO> createPlanet(@RequestBody PlanetDTO planetDTO) throws URISyntaxException {
//        if (planetDTO.getId() != null) {
//            throw new BadRequestAlertException("A new planet cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        PlanetDTO result = facade.save(planetDTO);
//        return ResponseEntity
//                .created(new URI("/api/planets/" + result.getId()))
//                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//                .body(result);
//    }
//
//    @PatchMapping(value = "/planets/{id}")
//    public ResponseEntity<PlanetDTO> partialUpdatePlanet(
//            @PathVariable(value = "id", required = false) final Long id,
//            @RequestBody PlanetDTO planetDTO
//    ) {
//        if (planetDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        if (!Objects.equals(id, planetDTO.getId())) {
//            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
//        }
//
//        Optional<PlanetDTO> result = facade.partialUpdate(planetDTO);
//        return ResponseEntity
//                .ok()
//                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).body(result.orElseThrow());
//    }
//
//
//    @GetMapping("/planets")
//    public ResponseEntity<Page<PlanetDTO>> getAllPlanets(Pageable pageable) {
//        Page<PlanetDTO> page = facade.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ResponseEntity.ok().headers(headers).body(page);
//    }
//
//
//    @GetMapping("/planets/{id}")
//    public ResponseEntity<PlanetDTO> getPlanet(@PathVariable Long id) {
//        var planetDTO = facade.findOne(id);
//        return ResponseEntity.ok().body(planetDTO);
//    }
//
//    //I did the delete method, because it was a study, but within the context it doesn't make sense and it wouldn't be called
//    @DeleteMapping("/planets/{id}")
//    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
//        facade.delete(id);
//        return ResponseEntity
//                .noContent()
//                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
//                .build();
//    }


    //todo escluir eh so para tester
//    @ApiOperation(value="Deleta um produto")
    @GetMapping("/test")
//    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public  String soParaTeste() {
        return "aqui";
    }
}