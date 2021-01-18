package com.example.demo.api;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    private static final String USAGE_TOPIC = "KafkaUsageAPI";
    private static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");

    @PostMapping
    public Movie addMovies(@RequestBody Movie movie){
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplateString.send(USAGE_TOPIC,"POST Movie API has been Accessed at : "+formatDateTime);
        return movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies() {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplateString.send(USAGE_TOPIC,"GET Movie API has been Accessed at : "+formatDateTime);
        return movieService.getMovies();
    }

    @GetMapping(value = "/{movieId}")
    public Movie getMovie(@PathVariable("movieId") int movieId) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplateString.send(USAGE_TOPIC,"GET BY ID Movie API has been Accessed at : "+formatDateTime);
        return movieService.getMovie(movieId);
    }

    @PutMapping(value = "/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId,@RequestBody Movie movie) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplateString.send(USAGE_TOPIC,"PUT Movie API has been Accessed at : "+formatDateTime);
        return movieService.updateMovie(movieId,movie);
    }

    @DeleteMapping(value = "/{movieId}")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(DATETIMEFORMATTER);
        kafkaTemplateString.send(USAGE_TOPIC,"DELETE Movie API has been Accessed at : "+formatDateTime);
        movieService.deleteMovie(movieId);
    }



}
