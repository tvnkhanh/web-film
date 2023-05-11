package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.dto.Databasehelper;
import ptit.wibulord.webfilm.model.Film;
import ptit.wibulord.webfilm.repository.FilmRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> getFilms(){
        return filmRepository.getListFilm();
    }
    public Film getFilmById(int id) {
        return filmRepository.findById(id).get();
    }

    public List<Film> getMovie() {
        return filmRepository.getFilmByType("MOVIE");
    }

    public List<Film> getRandom() {
        return filmRepository.getRandomFilm();
    }

    public List<Film> searchFilm(String keyword) {
        return filmRepository.searchByFilmName(keyword);
    }
}
