package com.example.demo.facade;

import com.example.demo.facade.dto.AdminUserDTO;
import com.example.demo.facade.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserFacade {

    private final UserMapper mapper;

    private final UserService service;

    public UserFacade(UserMapper mapper, UserService service) {
        this.mapper = mapper;
        this.service = service;
    }


    @Transactional(readOnly = true)
    public Page<AdminUserDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }



// todo, this is an example

//    private final Logger log = LoggerFactory.getLogger(ForceUserFacade.class);
//
//    private final ForceUserMapper mapper;
//
//    private final ForceUserService service;
//
//    public ForceUserFacade(ForceUserMapper mapper, ForceUserService service) {
//        this.mapper = mapper;
//        this.service = service;
//    }
//
//
//    public ForceUserDTO save(ForceUserDTO forceUserDTO) {
//        log.debug("Request to save ForceUser : {}", forceUserDTO);
//        service.checkByName(forceUserDTO.getName());
//        ForceUser forceUser = mapper.toEntity(forceUserDTO);
//        forceUser = service.save(forceUser);
//        return mapper.toDto(forceUser);
//    }
//
//    public Optional<ForceUserDTO> partialUpdate(ForceUserDTO forceUserDTO) {
//        log.debug("Request to partially update ForceUser : {}", forceUserDTO);
//        return service
//                .findOne(forceUserDTO.getId())
//                .map(
//                        existingForceUser -> {
//                            mapper.partialUpdate(existingForceUser, forceUserDTO);
//                            return existingForceUser;
//                        }
//                )
//                .map(service::save)
//                .map(mapper::toDto);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<ForceUserDTO> findAll(Pageable pageable) {
//        return service.findAll(pageable).map(mapper::toDto);
//    }
//
//    @Transactional(readOnly = true)
//    public ForceUserDTO findOne(Long id) {
//        log.debug("Request to get ForceUser : {}", id);
//        return mapper.toDto(service.findOne(id).orElseThrow());
//    }
//
//    public void delete(Long id) {
//        log.debug("Request to delete ForceUser : {}", id);
//        service.delete(id);
//    }
}
