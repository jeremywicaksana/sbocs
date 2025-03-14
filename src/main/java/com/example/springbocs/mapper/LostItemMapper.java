package com.example.springbocs.mapper;

import com.example.springbocs.model.dto.LostItemDto;
import com.example.springbocs.model.entity.LostItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LostItemMapper {

    LostItemMapper INSTANCE = Mappers.getMapper( LostItemMapper.class );

    @Mapping(target = "itemId", source = "id")
    @Mapping(target = "itemName", source = "name")
    @Mapping(target = "itemAmount", source = "quantity")
    @Mapping(target = "itemLocation", source = "place")
    LostItemDto lostItemToLostItemDto(LostItem lostItem);

    @Mapping(target = "id", source = "itemId")
    @Mapping(target = "name", source = "itemName")
    @Mapping(target = "quantity", source = "itemAmount")
    @Mapping(target = "place", source = "itemLocation")
    LostItem lostItemDtoToLostItem(LostItemDto lostItemDto);
}
