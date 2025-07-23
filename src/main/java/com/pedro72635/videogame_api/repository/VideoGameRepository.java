package com.pedro72635.videogame_api.repository;

import com.pedro72635.videogame_api.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
}