package com.example.demo.service.impl;

import com.example.demo.domain.Photo;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.service.PhotoService;
import liquibase.util.file.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
public class PhotoServiceImpl implements PhotoService {

    private static final String ENTITY_NAME = "photo";

    private final PhotoRepository repository;

    private final PoliticalServiceImpl politicalServiceImpl;

    public PhotoServiceImpl(PhotoRepository repository, PoliticalServiceImpl politicalServiceImpl) {
        this.repository = repository;
        this.politicalServiceImpl = politicalServiceImpl;
    }


    @Override
    public Photo save(Long id, MultipartFile imageFile) throws Exception {
        if(FilenameUtils.getExtension(imageFile.getOriginalFilename()).contains("png")  || FilenameUtils.getExtension(imageFile.getOriginalFilename()).contains("jpeg")) {
            // todo explicar isto
            String folder = System.getProperty("user.dir") + "/src/main/resources/static/img/" ;
            byte[] bytes = imageFile.getBytes();
            String fileName = randomAlphanumeric(24)+"."+ FilenameUtils.getExtension(imageFile.getOriginalFilename());
            Path path = Paths.get(folder + fileName);
            Files.write(path, bytes);
            Photo photo = new Photo();
            //talvez esta parte nao era nescessaria
            photo.setContentType(imageFile.getContentType());
            photo.setFileName(fileName);
            //explicar isto aqui
            photo.setUrl("/src/main/resources/static/img/" + fileName);
            photo.setPolitical(politicalServiceImpl.findOne(id).orElseThrow());

            return repository.save(photo);
        } else {
            return null;
        }
    }

    public Photo update(Long id, Long political){
        var photo = repository.findById(id).orElseThrow();
        photo.setPolitical(politicalServiceImpl.findOne(political).orElseThrow());
        return repository.save(photo);
    }



    @Override
    public Page<Photo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public byte[] findOne(Long id) throws IOException {
        File file = new File(System.getProperty("user.dir") + repository.findById(id).orElseThrow().getUrl());
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public void delete(Long id) {
        File file = new File(System.getProperty("user.dir") + repository.findById(id).orElseThrow().getUrl());
        file.delete();
        repository.deleteById(id);
    }
}
