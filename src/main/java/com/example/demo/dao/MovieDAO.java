package com.example.demo.dao;

import com.example.demo.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDAO extends CrudRepository<Movie, Integer> {

    @Override
    List<Movie> findAll();
}
