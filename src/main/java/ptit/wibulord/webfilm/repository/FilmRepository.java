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
    @Query("select f from Film f where f.filmName like %?1%")
    List<Film> searchByFilmName(String keyword);
    @Query("select count(f) from Film f")
    int countFilm();
    @Query("select f from Film f, BuyFilm b where b.user.idUser = :id  and f.filmID = b.film.filmID")
    List<Film> getMyFilm(int id);

//    @Query(nativeQuery = true,
//            value = "select top 24 p.f from (select f,  t.view  from Film f inner join  (select e.film.filmID as id , sum(e.view) as view from Episode e group by e.film.filmID) t where f.filmID = t.id order by t.view desc) p")
//    List<Film> get24FilmTopTier();
}
