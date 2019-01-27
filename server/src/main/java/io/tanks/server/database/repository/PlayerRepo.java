package io.tanks.server.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.tanks.server.database.domain.Player;

public interface PlayerRepo extends JpaRepository<Player, Long> {
}
