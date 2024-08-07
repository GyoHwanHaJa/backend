package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Schedule.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {
    private Long id;
    private String name;
    private Boolean isCustom;

    public static TagDto toTagDto(Tag tag) {
        TagDto tagDto = new TagDto();

        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        tagDto.setIsCustom(tag.getIsCustom());

        return tagDto;
    }
}