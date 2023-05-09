package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Film;
import ptit.wibulord.webfilm.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> getFilms(){
        return filmRepository.getListFilm();
    }
    public Film findFilmById(int id) {
        return filmRepository.findFilmByID(id);
    }

    public void saveFilm(Film film){
        filmRepository.save(film);
    }

    public void deleteFilmById(int id){
        filmRepository.deleteById(id);
    }
}
