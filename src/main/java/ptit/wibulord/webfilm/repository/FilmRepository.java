package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
}
