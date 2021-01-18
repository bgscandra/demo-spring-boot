package com.example.demo.api;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    private static final String USAGE_TOPIC = "KafkaUsageAPI";

    @PostMapping
    public Movie addMovies(@RequestBody Movie movie){
        kafkaTemplateString.send(USAGE_TOPIC,"POST Movie API has been Accessed");
        return movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies() {
        kafkaTemplateString.send(USAGE_TOPIC,"GET Movie API has been Accessed");
        return movieService.getMovies();
    }

    @GetMapping(value = "/{movieId}")
    public Movie getMovie(@PathVariable("movieId") int movieId) {
        kafkaTemplateString.send(USAGE_TOPIC,"GET BY ID Movie API has been Accessed");
        return movieService.getMovie(movieId);
    }

    @PutMapping(value = "/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId,@RequestBody Movie movie) {
        kafkaTemplateString.send(USAGE_TOPIC,"PUT Movie API has been Accessed");
        return movieService.updateMovie(movieId,movie);
    }

    @DeleteMapping(value = "/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        kafkaTemplateString.send(USAGE_TOPIC,"DELETE Movie API has been Accessed");
        movieService.deleteMovie(movieId);
    }



}
