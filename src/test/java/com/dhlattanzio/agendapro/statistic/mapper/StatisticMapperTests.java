package com.dhlattanzio.agendapro.statistic.mapper;

import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;
import com.dhlattanzio.agendapro.statistic.persistence.entity.Statistic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StatisticMapperTests {
    @InjectMocks
    private StatisticMapper statisticMapper;

    @Test
    public void whenMapEntityToDto_thenReturnStatisticDto() {
        Statistic statistic = new Statistic();
        statistic.setCategory("Category");
        statistic.setCount(10L);

        StatisticDto statisticDto = statisticMapper.toDto(statistic);

        assertNotNull(statisticDto);
        assertEquals(statistic.getCategory(), statisticDto.category());
        assertEquals(statistic.getCount(), statisticDto.count());
    }
}
