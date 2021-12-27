package com.example.demo.service;

import com.example.demo.domain.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {

    Photo save(Long id, MultipartFile imageFile) throws Exception;

    Photo update(Long id, Long political);

    Page<Photo> findAll(Pageable pageable);

    byte[] findOne(Long id) throws IOException;

    void delete(Long id);
}
