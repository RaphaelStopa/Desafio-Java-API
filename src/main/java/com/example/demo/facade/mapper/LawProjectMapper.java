package com.example.demo.facade.mapper;

import com.example.demo.domain.LawProject;
import com.example.demo.facade.dto.LawProjectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PoliticalMapper.class})
public interface LawProjectMapper extends EntityMapper<LawProjectDTO, LawProject>  {
}
