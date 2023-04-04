package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
    @Query("select f from Film f")
    List<Film> getListFilm();
}
