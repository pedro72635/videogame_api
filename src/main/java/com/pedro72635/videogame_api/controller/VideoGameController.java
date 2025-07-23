package com.pedro72635.videogame_api.controller;

import com.pedro72635.videogame_api.model.VideoGame;
import com.pedro72635.videogame_api.repository.VideoGameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videogames")
public class VideoGameController {

    @Autowired
    private VideoGameRepository videoGameRepository;

    // GET: Listar todos los videojuegos
    @GetMapping
    public List<VideoGame> getAllVideoGames() {
        return videoGameRepository.findAll();
    }

    // GET: Obtener un videojuego por ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoGame> getVideoGameById(@PathVariable Long id) {
        Optional<VideoGame> videoGame = videoGameRepository.findById(id);
        return videoGame.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo videojuego
    @PostMapping
    public VideoGame createVideoGame(@RequestBody VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }

    // PUT: Actualizar un videojuego
    @PutMapping("/{id}")
    public ResponseEntity<VideoGame> updateVideoGame(@PathVariable Long id, @RequestBody VideoGame videoGameDetails) {
        Optional<VideoGame> videoGame = videoGameRepository.findById(id);
        if (videoGame.isPresent()) {
            VideoGame updatedVideoGame = videoGame.get();
            updatedVideoGame.setTitle(videoGameDetails.getTitle());
            updatedVideoGame.setGenre(videoGameDetails.getGenre());
            updatedVideoGame.setPlatform(videoGameDetails.getPlatform());
            updatedVideoGame.setReleaseYear(videoGameDetails.getReleaseYear());
            updatedVideoGame.setDeveloper(videoGameDetails.getDeveloper());
            return ResponseEntity.ok(videoGameRepository.save(updatedVideoGame));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: Eliminar un videojuego
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoGame(@PathVariable Long id) {
        if (videoGameRepository.existsById(id)) {
            videoGameRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}