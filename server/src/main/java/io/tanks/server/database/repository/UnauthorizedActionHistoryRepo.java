package io.tanks.server.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.tanks.server.database.domain.UnauthorizedActionHistory;

public interface UnauthorizedActionHistoryRepo extends JpaRepository<UnauthorizedActionHistory, Long> {
}
