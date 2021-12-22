package com.example.demo.facade.mapper;

import com.example.demo.domain.Phone;
import com.example.demo.facade.dto.PhoneDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PhoneMapper extends EntityMapper<PhoneDTO, Phone>  {
}
