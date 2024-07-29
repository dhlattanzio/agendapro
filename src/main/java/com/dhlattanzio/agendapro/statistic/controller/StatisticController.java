package com.dhlattanzio.agendapro.statistic.controller;

import com.dhlattanzio.agendapro.shared.dto.ListResponse;
import com.dhlattanzio.agendapro.statistic.controller.dto.StatisticDto;
import com.dhlattanzio.agendapro.statistic.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticController {
    private final StatisticService statisticService;

    @Operation(summary = "Get all statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public ListResponse<StatisticDto> getAll() {
        return new ListResponse<>(statisticService.getAll());
    }
}
