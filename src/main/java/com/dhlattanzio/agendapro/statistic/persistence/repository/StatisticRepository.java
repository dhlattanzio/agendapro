package com.dhlattanzio.agendapro.statistic.persistence.repository;

import com.dhlattanzio.agendapro.statistic.persistence.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, String> {
}
