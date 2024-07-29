package com.dhlattanzio.agendapro.statistic.controller;

import com.dhlattanzio.agendapro.shared.dto.ListResponse;
import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;
import com.dhlattanzio.agendapro.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping
    public ListResponse<StatisticDto> getAll() {
        return new ListResponse<>(statisticService.getAll());
    }
}
