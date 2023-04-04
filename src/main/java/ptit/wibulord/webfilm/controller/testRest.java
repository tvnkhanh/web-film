package ptit.wibulord.webfilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.wibulord.webfilm.model.Film;
import ptit.wibulord.webfilm.service.FilmService;

import java.util.List;

@RestController
public class testRest {

    @Autowired
    FilmService filmService;

    @GetMapping("/film")
    public List<Film> getListFilm(){
        System.out.println(filmService.getFilms());
        return null;
    }
}
