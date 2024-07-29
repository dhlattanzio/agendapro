package com.dhlattanzio.agendapro.statistic.mapper;

import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;
import com.dhlattanzio.agendapro.statistic.persistence.entity.Statistic;
import org.springframework.stereotype.Component;

@Component
public class StatisticMapper {
    public StatisticDto toDto(Statistic statistic) {
        return new StatisticDto(statistic.getCategory(), statistic.getCount());
    }
}
