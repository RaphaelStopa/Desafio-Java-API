package com.example.demo.facade.mapper;

import com.example.demo.domain.Phone;
import com.example.demo.domain.Political;
import com.example.demo.facade.dto.PhoneDTO;
import com.example.demo.facade.dto.PoliticalToUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PoliticalIdMapper.class})
public interface PhoneMapper extends EntityMapper<PhoneDTO, Phone>  {

}
