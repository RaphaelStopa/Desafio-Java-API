package com.example.demo.service.impl;

import com.example.demo.domain.Authority;
import com.example.demo.domain.User;
import com.example.demo.facade.dto.AdminUserDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.querydsl.UserQueryRepository;
import com.example.demo.security.AuthoritiesConstants;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.UserService;

import com.example.demo.service.exceptions.BadRequestAlertException;
import com.example.demo.service.exceptions.BusinessException;
import com.example.demo.service.exceptions.enumeration.ErrorConstants;
import com.example.demo.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//todo transicional novamente, ver se com o facede acaba
@Service
@Transactional
public class UserServiceImpl implements UserService {
    //equivale a UserService

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String ENTITY_NAME = "user";

    private final UserRepository userRepository;


    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserQueryRepository userQueryRepository;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserQueryRepository userQueryRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userQueryRepository = userQueryRepository;
    }



    //todo fiz assim mais precisa ser ajustado quande parte, eh que no caso tenho que c0onsiderar emial
    //ber aqui um erro nao cai nas exesc
    public User registerUser(AdminUserDTO userDTO, String password) {

        // todo, evitar logins com estacos e caracteres especiais e tem outro erro, ele nao tem que ser validado o emial
        //nao eesta funconando coreetamente esta validacaoes eh algo no regex os audits tambem nao fincionam mas estes acho que sao procedures no db
        // ver caches tambem
        // nao esta fvaliodando email algum nem logim
        //permite o envio vazio

//        System.out.println(userQueryRepository.findOneByEmailIgnoreCase("admin@localhost"));

        if (Optional.ofNullable(userDTO.getLogin()).isEmpty()) {
            throw new BadRequestAlertException("need a login", ENTITY_NAME, "nonexistent login");
        }

        if (userDTO.getLogin().isBlank()) {
            throw new BadRequestAlertException("can not be blank", ENTITY_NAME, "nonexistent login");
        }

        if (Optional.ofNullable(userDTO.getEmail()).isEmpty()) {
            throw new BadRequestAlertException("need a email", ENTITY_NAME, "nonexistent email");
        }

        if (userDTO.getEmail().isBlank()) {
            throw new BadRequestAlertException("can not be blank", ENTITY_NAME, "nonexistent email");
        }

        //I put a simple email validation, for the front would already be able to validate the email and
        // even if I don't, the account was only to appear as active after the account confirmation

        userRepository
                .findOneByLogin(userDTO.getLogin().toLowerCase())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new BadRequestAlertException("Login already used", ENTITY_NAME, "Login repeated");
                    }
                });
        userRepository
                .findOneByEmailIgnoreCase(userDTO.getEmail())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw BusinessException.by(ErrorConstants.EMAIL_ALREADY_IN_USE).get();
                    }
                });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(userDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        newUser.setId(null);
        newUser.setCreatedBy("nha"); //isto tambem precisa ser ajustado
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }


    //TODO Caches
    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        //this.clearUserCaches(existingUser);
        return true;
    }


    //todo trns
    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw BusinessException.by(ErrorConstants.INVALID_PASSWORD).get();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    log.debug("Changed password for User: {}", user);
                });
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public String findEmailByLogin(String login) {
        // todo soltar um trow nao precisa estoura sozinho
        return userRepository.findOneByLogin(login).orElseThrow().getEmail();
    };


//    private static final String ENTITY_NAME = "forceUser";
//
//    private final ForceUserRepository repository;
//
//    private final TypeForceUserServiceImpl typeForceUserRepository;
//
//    private final KnownMastersServiceImpl knownMastersService;
//
//    public ForceUserServiceImpl(ForceUserRepository repository, TypeForceUserServiceImpl typeForceUserRepository, KnownMastersServiceImpl knownMastersService) {
//        this.repository = repository;
//        this.typeForceUserRepository = typeForceUserRepository;
//        this.knownMastersService = knownMastersService;
//    }
//
//    @Override
//    public ForceUser save(ForceUser forceUser) {
//        return repository.save(forceUser);
//    }
//
//    @Override
//    public Optional<ForceUser> partialUpdate(ForceUser forceUser) {
//        if (!repository.existsById(forceUser.getId())) {
//            throw BusinessException.badRequest();
//        }
//        return Optional.of(repository.save(forceUser));
//    }
//
//
//    @Override
//    public Page<ForceUser> findAll(@NonNull Pageable pageable) {
//        return repository.findAll(pageable);
//    }
//
//    @Override
//    public Optional<ForceUser> findOne(@NonNull Long id) {
//        return repository.findById(id);
//    }
//
//
//    @Override
//    public void checkByName(String name) {
//
//        if (Optional.ofNullable(name).isEmpty()) {
//            throw new BadRequestAlertException("need a name", ENTITY_NAME, "nonexistent name");
//
//        }
//
//        if (name.isBlank()) {
//            throw new BadRequestAlertException("need a name", ENTITY_NAME, "nonexistent name");
//
//        }
//
//        if (!repository.findByName(name).isEmpty()) {
//            throw new BadRequestAlertException("already exists with this name", ENTITY_NAME, "repeated name");
//
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        typeForceUserRepository.findAllByForceUserId(id).forEach(it-> typeForceUserRepository.delete(it.getId()));
//        knownMastersService.findAllByMasterId(id).forEach(it-> knownMastersService.delete(it.getId()));
//        knownMastersService.findAllByMasterId(id).forEach(it-> knownMastersService.delete(it.getId()));
//        repository.deleteById(id);
//    }
}
