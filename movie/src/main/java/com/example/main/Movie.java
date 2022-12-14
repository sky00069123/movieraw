package com.example.main;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;


//@Builder
@Table(name = "movies")
@Entity
public class Movie {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String title;
	private int year;
	private String cast;
	private String genres;


	public Movie() {}

	public Movie(Long id, String title, int year, String cast, String genres) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.cast = cast;
		this.genres = genres;
	}

	public Long getId() {
        return this.id;
    }

	public String getTitle() {
		return this.title;
	}

	public int getYear() {
		return this.year;
	}

	public String getCast() {
		return this.cast;
	}

	public String getGenres() {
		return this.genres;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Movie))
			return false;
			Movie movie = (Movie) o;
		return Objects.equals(this.title, movie.title)
				&& Objects.equals(this.year, movie.year);
	}

	@Override
	public String toString() {
		return "Movie{" + 
				", id=" + this.title + '\'' + 
				", title='" + this.title + '\'' + 
				", year='" + this.year + '\'' + 
				", cast=" + this.cast + '\'' + 
				", genres=" + this.genres + '\'' + 
				'}';
	}
}
