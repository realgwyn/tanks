package io.tanks.server.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.tanks.server.database.domain.MalformedPacketHistory;

public interface MalformedPacketHistoryRepo extends JpaRepository<MalformedPacketHistory, Long> {
}
