package com.example.demo.api;

import com.example.demo.model.Customer;
import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private KafkaTemplate<String, Movie> kafkaTemplateMovie;

    private static final String TOPIC = "KafkaAPI";
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");

    @PostMapping
    public Movie addMovies(@RequestBody Movie movie){
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplateMovie.send(TOPIC, movie);
        log.info("POST Movie API has been Accessed at : "+formatDateTime);
        return movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies() {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        log.info("GET Movie API has been Accessed at : "+formatDateTime);
        return movieService.getMovies();
    }

    @GetMapping(value = "/{movieId}")
    public Movie getMovie(@PathVariable("movieId") int movieId) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        log.info("GET BY ID Movie API has been Accessed at : "+formatDateTime);
        return movieService.getMovie(movieId);
    }

    @PutMapping(value = "/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId,@RequestBody Movie movie) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        log.info("PUT Movie API has been Accessed at : "+formatDateTime);
        return movieService.updateMovie(movieId,movie);
    }

    @DeleteMapping(value = "/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        log.warn("POST Movie API has been Accessed at : "+formatDateTime);
        movieService.deleteMovie(movieId);
    }



}
