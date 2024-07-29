package com.dhlattanzio.agendapro.statistic.service.impl;

import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;
import com.dhlattanzio.agendapro.statistic.mapper.StatisticMapper;
import com.dhlattanzio.agendapro.statistic.persistence.entity.Statistic;
import com.dhlattanzio.agendapro.statistic.persistence.repository.StatisticRepository;
import com.dhlattanzio.agendapro.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;

    @Override
    public List<StatisticDto> getAll() {
        return statisticRepository.findAll().stream()
                .map(statisticMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public void incrementCounter(String category) {
        Statistic statistic = getOrCreate(category);
        statistic.setCount(statistic.getCount() + 1);

        statisticRepository.save(statistic);
    }

    @Transactional
    @Override
    public void decrementCounter(String category) {
        Statistic statistic = getOrCreate(category);

        if (statistic.getCount() == 0) {
            log.warn("Statistic count is already 0 for category: {}", category);
            return;
        }
        statistic.setCount(statistic.getCount() - 1);

        statisticRepository.save(statistic);
    }

    @Transactional
    @Override
    public void incrementAndDecrementCounter(String incrementCategory, String decrementCategory) {
        if (!incrementCategory.equals(decrementCategory)) {
            incrementCounter(incrementCategory);
            decrementCounter(decrementCategory);
        }
    }

    private Statistic getOrCreate(String category) {
        return statisticRepository.findById(category)
                .orElseGet(() -> new Statistic(category, 0L));
    }
}
