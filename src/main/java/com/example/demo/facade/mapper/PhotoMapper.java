package com.example.demo.facade.mapper;


import com.example.demo.domain.Photo;
import com.example.demo.facade.dto.PhotoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PoliticalIdMapper.class})
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo>  {
}
