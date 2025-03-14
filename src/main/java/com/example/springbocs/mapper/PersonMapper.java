package com.example.springbocs.mapper;

import com.example.springbocs.model.dto.PersonDto;
import com.example.springbocs.model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );

    PersonDto PersonToPersonDto(Person person);
}
