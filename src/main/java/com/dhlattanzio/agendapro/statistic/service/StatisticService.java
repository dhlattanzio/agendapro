package com.dhlattanzio.agendapro.statistic.service;

import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;

import java.util.List;

public interface StatisticService {
    List<StatisticDto> getAll();
    void incrementCounter(String category);
    void decrementCounter(String category);
    void incrementAndDecrementCounter(String incrementCategory, String decrementCategory);
}
