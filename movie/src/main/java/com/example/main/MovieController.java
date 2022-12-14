package com.example.main;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.main.s3.MovieS3;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/")
	public String index() {
		return "Hello World movie demo!";
	}

	@GetMapping("/health")
	public String getHealth() {
		//log.info("GET /movies");
		return "healthy";
	}

    @GetMapping("/movie/count")
    public String getCount() {
        return movieService.getCount().toString();
    }

	@GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable Long id){
        return movieService.findMovie(id);
    }

    @PostMapping("/movie")
	public Movie saveMovie(
        @RequestBody Movie movie)
    {
        return movieService.saveMovie(movie);
    }

    @PutMapping("/movie/{id}")
    public Movie
    updateMovie(@RequestBody Movie movie,
                     @PathVariable("id") Long id)
    { 
        return movieService.updateMovie(
            movie, id);
    }
  
    @DeleteMapping("/movie/{id}")
    public String deleteMovieById(@PathVariable("id")
                                       Long id)
    {
        movieService.deleteMovieById(
            id);
        return "Deleted Successfully";
    }

    @GetMapping("/movie/title")
	public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam String title) {
		return new ResponseEntity<List<Movie>>(movieService.findByTitleContains(title), HttpStatus.OK);
	}

    @GetMapping("/movie/year")
	public ResponseEntity<List<Movie>> getMovieByYear(@RequestParam int year) {
		return new ResponseEntity<List<Movie>>(movieService.findByYear(year), HttpStatus.OK);
	}

    @GetMapping("/movie/cast")
	public ResponseEntity<List<Movie>> getMovieByCast(@RequestParam String cast) {
		return new ResponseEntity<List<Movie>>(movieService.findByCastContains(cast), HttpStatus.OK);
	}

    @GetMapping("/movie/genres")
	public ResponseEntity<List<Movie>> getMovieByGenres(@RequestParam String genres) {
		return new ResponseEntity<List<Movie>>(movieService.findByGenresContains(genres), HttpStatus.OK);
	}


    @Autowired
    MovieS3 movieS3;

    // will overwrite existing file on S3 and will create a new entry in DB
    @PostMapping("/movie/upload")
    public ResponseEntity<String> saveMovieS3(@RequestParam("fileName") String fileName,
            @RequestParam("file") MultipartFile file) {

                ObjectMapper objectMapper = new ObjectMapper();

                Movie movie;
                try {
                    movie = objectMapper.readValue(file.getInputStream(), Movie.class);
                    movieService.saveMovie(movie);
                } catch (StreamReadException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (DatabindException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 

        return new ResponseEntity<>(movieS3.uploadFile(fileName, MultipartFile.class.cast(file)), HttpStatus.OK);
    }
    
    
}