package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptit.wibulord.webfilm.model.Episode;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    @Query("SELECT T FROM Episode AS T ORDER BY T.epNum DESC, T.datePosted DESC")
    List<Episode> getNewestEpisodes();
}
