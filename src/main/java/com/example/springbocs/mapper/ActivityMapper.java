package com.example.springbocs.mapper;

import com.example.springbocs.model.dto.ActivityDto;
import com.example.springbocs.model.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityMapper {

    ActivityMapper INSTANCE = Mappers.getMapper( ActivityMapper.class );

    ActivityDto activityToActivityDto(Activity activity);
}
