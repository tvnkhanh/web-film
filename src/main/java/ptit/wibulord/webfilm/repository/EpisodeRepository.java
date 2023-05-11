package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptit.wibulord.webfilm.model.Episode;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    @Query("SELECT ep FROM Episode AS ep ORDER BY ep.datePosted DESC, ep.epNum DESC")
    List<Episode> getNewestEpisodes();
    @Query("select t from  Episode as t where t.epID = :id")
    Episode findById(@Param("id")int id);
}
