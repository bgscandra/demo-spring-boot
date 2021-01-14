package com.example.demo.api;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie addMovies(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping(value = "/{movieId}")
    public Movie getMovie(@PathVariable("movieId") int movieId) {
        return movieService.getMovie(movieId);
    }

    @PutMapping(value = "/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId,@RequestBody Movie movie) {
        return movieService.updateMovie(movieId,movie);
    }

    @DeleteMapping(value = "/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        movieService.deleteMovie(movieId);
    }



}
