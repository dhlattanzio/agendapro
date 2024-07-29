package com.dhlattanzio.agendapro.shared.dto;

import java.util.List;

public record ListResponse<T>(List<T> items) {}
