package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.FavoriteList;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList,Integer> {
}
