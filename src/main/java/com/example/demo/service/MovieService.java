package com.example.demo.service;

import com.example.demo.dao.MovieDAO;
import com.example.demo.exception.MovieNotFoundException;
import com.example.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;

    public Movie addMovie(Movie movie) {
        return movieDAO.save(movie);
    }

    public List<Movie> getMovies() {
        return movieDAO.findAll();
    }

    public Movie getMovie(int movieId){
        Optional<Movie> optionalMovie = movieDAO.findById(movieId);
        if(!optionalMovie.isPresent())
            throw new MovieNotFoundException("Movie Not Found");
        return optionalMovie.get();
    }

    public Movie updateMovie(int movieId,Movie movie) {
        movie.setMovieId(movieId);
        return movieDAO.save(movie);
    }

    public void deleteMovie(int movieId) {
        movieDAO.deleteById(movieId);
    }
}
