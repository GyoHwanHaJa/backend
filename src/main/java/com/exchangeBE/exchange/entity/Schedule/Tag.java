package com.exchangeBE.exchange.entity.Schedule;

import com.exchangeBE.exchange.dto.TagDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isCustom;

    @OneToMany(mappedBy = "tag")
    private Set<ScheduleTag> scheduleTags = new HashSet<>();

    public static Tag toTagEntity(TagDto tagDto) {
        Tag tag = new Tag();

        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        tag.setIsCustom(tagDto.getIsCustom());

        return tag;
    }
}
