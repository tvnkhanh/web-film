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

    @Query("select f from Film f where f.type = :type order by RAND() limit 6")
    List<Film> getFilmByType(String type);

    @Query("select f from Film f order by RAND() limit 6")
    List<Film> getRandomFilm();

    @Query("select f from Film f where f.filmName like %?1%")
    List<Film> searchByFilmName(String keyword);
}
