package com.example.demo.facade.mapper;

import com.example.demo.domain.Political;
import com.example.demo.facade.dto.PoliticalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PoliticalMapper extends EntityMapper<PoliticalDTO, Political>  {
}
