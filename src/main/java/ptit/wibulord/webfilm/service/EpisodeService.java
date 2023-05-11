package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.dto.Databasehelper;
import ptit.wibulord.webfilm.model.Episode;
import ptit.wibulord.webfilm.model.Film;
import ptit.wibulord.webfilm.repository.EpisodeRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;
    @Autowired
    FilmService filmService;

    public Episode getEpById(int id) {
        return episodeRepository.findById(id);
    }

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
    public void addEps(Episode eps){
        episodeRepository.save(eps);
    }
    public Episode findEpsById(int id){
        return episodeRepository.findById(id);
    }
    public void deleteEps(int id) {
        episodeRepository.deleteById(id);
    }

    public List<Film> getTierList() {
        List<Film> tierList = new ArrayList<>();
        HashMap<Integer, Integer> topFilmIds = new HashMap<>();

        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT TOP 10 ID_PHIM, SUM(LUOTXEM) AS LUOTXEM FROM TAP GROUP BY ID_PHIM ORDER BY LUOTXEM DESC";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                topFilmIds.put(resultSet.getInt(1), resultSet.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        topFilmIds.forEach((id, view) -> {
            Film film = filmService.getFilmById(id);
            tierList.add(film);
        });

        return tierList;
    }
}
