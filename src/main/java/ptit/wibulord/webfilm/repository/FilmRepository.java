package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Film;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
    @Query("select f from Film f")
    List<Film> getListFilm();
    @Query("select f from Film f where f.filmID = :id")
    Film findFilmByID(@Param("id") int id);

    @Query("select f from Film f where f.type = :type order by RAND() limit 6")
    List<Film> getFilmByType(String type);

    @Query("select f from Film f order by RAND() limit 6")
    List<Film> getRandomFilm();

    @Query("select coalesce(max(f.filmID), 0) from Film f ")
    public int findMaxId();
}
