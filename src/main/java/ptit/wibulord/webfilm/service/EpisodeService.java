package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Episode;
import ptit.wibulord.webfilm.model.Film;
import ptit.wibulord.webfilm.repository.EpisodeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;

    public List<Episode> getNewestList() {
        List<Episode> result = new ArrayList<>();
        List<Episode> rawData = episodeRepository.getNewestEpisodes();
        List<Film> containedFilm = new ArrayList<>();

        rawData.forEach((episode) -> {
            if (!containedFilm.contains(episode.getFilm())) {
                result.add(episode);
                containedFilm.add(episode.getFilm());
            }
        });

        return result;
    }
}
