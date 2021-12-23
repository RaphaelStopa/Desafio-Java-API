package com.example.demo.facade.mapper;

import com.example.demo.domain.Political;
import com.example.demo.domain.PoliticalParty;
import com.example.demo.facade.dto.PoliticalDTO;
import com.example.demo.facade.dto.PoliticalForUserDTO;
import com.example.demo.facade.dto.PoliticalToUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PoliticalPartyMapper.class})
public interface PoliticalMapper extends EntityMapper<PoliticalDTO, Political>  {

    PoliticalToUpdateDTO toUpdateDto(Political political);

    PoliticalForUserDTO toUserDto(Political political);
}
