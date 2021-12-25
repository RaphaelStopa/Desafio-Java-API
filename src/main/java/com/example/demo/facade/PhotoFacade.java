package com.example.demo.facade;

import com.example.demo.facade.dto.PhotoDTO;
import com.example.demo.facade.mapper.PhotoMapper;
import com.example.demo.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class PhotoFacade {

    private final Logger log = LoggerFactory.getLogger(PhotoFacade.class);

    private final PhotoMapper mapper;

    private final PhotoService service;

    public PhotoFacade(PhotoMapper mapper, PhotoService service) {
        this.mapper = mapper;
        this.service = service;
    }

    public PhotoDTO save(String politicalId, MultipartFile photo) throws Exception {
        log.debug("Request to save Photo");
        Long id = Long.parseLong(politicalId);
        var photoSaved = service.save(id, photo);
        return mapper.toDto(photoSaved);
    }

    public PhotoDTO partialUpdate(PhotoDTO photoDTO) {
        log.debug("Request to partially update Photo : {}", photoDTO);
        return mapper.toDto(service.update(photoDTO.getId(), photoDTO.getPolitical().getId()));
    }


    @Transactional(readOnly = true)
    public Page<PhotoDTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toDto);
    }


    @Transactional(readOnly = true)
    public byte[] findOne(Long id) throws IOException {
        log.debug("Request to get Photo : {}", id);
        return service.findOne(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Photo : {}", id);
        service.delete(id);
    }
}
