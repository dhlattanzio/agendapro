package com.dhlattanzio.agendapro.statistic.service;

import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;
import com.dhlattanzio.agendapro.statistic.mapper.StatisticMapper;
import com.dhlattanzio.agendapro.statistic.persistence.entity.Statistic;
import com.dhlattanzio.agendapro.statistic.persistence.repository.StatisticRepository;
import com.dhlattanzio.agendapro.statistic.service.impl.StatisticServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTests {
    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Mock
    private StatisticRepository statisticRepository;

    @Mock
    private StatisticMapper statisticMapper;

    @Test
    public void whenGetAll_thenReturnsAllStatistics() {
        Statistic statistic1 = new Statistic();
        Statistic statistic2 = new Statistic();
        when(statisticRepository.findAll()).thenReturn(List.of(statistic1, statistic2));

        List<StatisticDto> result = statisticService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void whenIncrementCounter_thenIncrementCounterByOne() {
        String category = "category";
        Statistic statistic = new Statistic(category, 0L);
        when(statisticRepository.findById(category)).thenReturn(Optional.of(statistic));

        statisticService.incrementCounter(category);

        assertEquals(1, statistic.getCount());
    }

    @Test
    public void whenDecrementCounter_thenDecrementCounterByOne() {
        String category = "category";
        Statistic statistic = new Statistic(category, 1L);
        when(statisticRepository.findById(category)).thenReturn(Optional.of(statistic));

        statisticService.decrementCounter(category);

        assertEquals(0, statistic.getCount());
    }

    @Test
    public void whenIncrementAndDecrementCounter_thenIncrementAndDecrementCounters() {
        String incrementCategory = "incrementCategory";
        String decrementCategory = "decrementCategory";
        Statistic incrementStatistic = new Statistic(incrementCategory, 0L);
        Statistic decrementStatistic = new Statistic(decrementCategory, 1L);
        when(statisticRepository.findById(incrementCategory)).thenReturn(Optional.of(incrementStatistic));
        when(statisticRepository.findById(decrementCategory)).thenReturn(Optional.of(decrementStatistic));

        statisticService.incrementAndDecrementCounter(incrementCategory, decrementCategory);

        assertEquals(1, incrementStatistic.getCount());
        assertEquals(0, decrementStatistic.getCount());
    }
}
