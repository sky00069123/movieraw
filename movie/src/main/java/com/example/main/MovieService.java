package com.example.main;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    
    public Movie findMovie(Long id){
        return movieRepository.findById(id).get();
    }

    public Long getCount(){
        return movieRepository.count();
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
  
    public List<Movie> fetchMovieList() {
        return (List<Movie>) movieRepository.findAll();
    }
  
    public Movie updateMovie(Movie movie, Long id) {
        Movie movieDB = movieRepository.findById(id).get();
  
        if (Objects.nonNull(movie.getTitle()) && !"".equalsIgnoreCase(movie.getTitle())) {
            movieDB.setTitle(movie.getTitle());
        }
  
        if (Objects.nonNull(movie.getYear()) && !"".equals(movie.getYear())) {
            movieDB.setYear(movie.getYear());
        }

        if (Objects.nonNull(movie.getCast()) && !"".equalsIgnoreCase(movie.getCast())) {
            movieDB.setCast(movie.getCast());
        }

        if (Objects.nonNull(movie.getGenres()) && !"".equalsIgnoreCase(movie.getGenres())) {
            movieDB.setGenres(movie.getGenres());
        }
  
        return movieRepository.save(movieDB);
    }
  
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> findByTitleContains(String title) {
        return (movieRepository.findByTitleContains(title));
    }

    public List<Movie> findByYear(int year) {
        return (movieRepository.findByYear(year));
    }

    public List<Movie> findByCastContains(String cast) {
        return (movieRepository.findByCastContains(cast));
    }

    public List<Movie> findByGenresContains(String genres) {
        return (movieRepository.findByGenresContains(genres));
    }    

}
