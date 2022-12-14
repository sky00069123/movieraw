package com.example.main;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
  
    public List<Movie> findByTitleContains(String title);

    public List<Movie> findByYear(int year);

    public List<Movie> findByCastContains(String cast);

    public List<Movie> findByGenresContains(String genres);
}