package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.WatchList;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList,Integer> {
}
