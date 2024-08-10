package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MainPageDto {
    private Long dDay;
    private Integer count;
    private List<Integer> dayList;
    private List<String> scheduleNameList;
    private List<LocalDateTime> scheduleTimeList;
}
