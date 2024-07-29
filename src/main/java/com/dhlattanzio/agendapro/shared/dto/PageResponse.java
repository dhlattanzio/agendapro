package com.dhlattanzio.agendapro.shared.dto;

import java.util.List;

public record PageResponse<T>(int page, int totalPages, long totalItems, List<T> items) {}
