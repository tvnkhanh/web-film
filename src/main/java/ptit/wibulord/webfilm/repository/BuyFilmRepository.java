package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptit.wibulord.webfilm.model.BuyFilm;

public interface BuyFilmRepository extends JpaRepository<BuyFilm, Integer> {
    @Query("select b from BuyFilm b where b.film.filmID = :filmId and b.user.idUser = :idUser")
    BuyFilm checkFilm(int filmId, int idUser);
}
