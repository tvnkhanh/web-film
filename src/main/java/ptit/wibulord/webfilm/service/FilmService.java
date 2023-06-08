package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.dto.Databasehelper;
import ptit.wibulord.webfilm.model.BuyFilm;
import ptit.wibulord.webfilm.model.Film;
import ptit.wibulord.webfilm.repository.FilmRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> getFilms(){
        return filmRepository.getListFilm();
    }


    public void saveFilm(Film film){
        filmRepository.save(film);
    }

    public void deleteFilm(Film film) {
        filmRepository.delete(film);
    }
    public Film getFilmById(int id) {
        return filmRepository.findFilmByID(id);
    }

    public List<Film> getMovie() {
        return filmRepository.getFilmByType("MOVIE");
    }

    public List<Film> getRandom() {
        return filmRepository.getRandomFilm();
    }
    public List<Film> get24FilmTopTier() {
        List<Film> tierList = new ArrayList<>();
        List<Integer> topFilmIds = new ArrayList<>();

        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT TOP 24 ID_PHIM, SUM(LUOTXEM) AS LUOTXEM FROM TAP GROUP BY ID_PHIM ORDER BY LUOTXEM DESC";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                topFilmIds.add(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i : topFilmIds){
            Film film = getFilmById(i);
            tierList.add(film);
        }
        return tierList;
    }
    public int getPageByCategory(String categoryName) {
        int count = 0;
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "select ceiling(CONVERT(float,count(p.ID_PHIM))/24) from PHIM p, THELOAI t, CT_THELOAI ct " +
                    "where t.ID_TL = ct.ID_TL and p.ID_PHIM = ct.ID_PHIM and t.TEN_TL = '"+categoryName+"'";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Film> getFilmPageByCategory(int page,String categoryName){
        List<Film> filmList = new ArrayList<>();
        List<Integer> filmIds = new ArrayList<>();
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from \n" +
                    "(select Row_Number() over (order by CT.ID_PHIM) as RowNumber, CT.ID_PHIM FROM CT_THELOAI CT, THELOAI TL " +
                    "WHERE CT.ID_TL = TL.ID_TL AND TL.TEN_TL = N'"+categoryName+"') as PagedTable\n" +
                    "where RowNumber between "+(page*24-23)+" and "+page*24;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                filmIds.add(resultSet.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i : filmIds){
            Film film = getFilmById(i);
            filmList.add(film);
        }
        return filmList;
    }

    public List<Film> getFilmByPage(int page){
        List<Film> filmList = new ArrayList<>();
        List<Integer> filmIds = new ArrayList<>();
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from \n" +
                    "(select Row_Number() over (order by ID_PHIM) as RowNumber, ID_PHIM from PHIM) as PagedTable\n" +
                    "where RowNumber between "+(page*24-23)+" and "+page*24;;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                filmIds.add(resultSet.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i : filmIds){
            Film film = getFilmById(i);
            filmList.add(film);
        }
        return filmList;
    }
    public List<Film> getFilmPageByType(int page,String type ){
        List<Film> filmList = new ArrayList<>();
        List<Integer> filmIds = new ArrayList<>();
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from \n" +
                    "(select Row_Number() over (order by ID_PHIM) as RowNumber, ID_PHIM from PHIM where LOAI = N'" + type+"') " +
                    "as PagedTable\n" +
                    "where RowNumber between "+(page*24-23)+" and "+page*24;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                filmIds.add(resultSet.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i : filmIds){
            Film film = getFilmById(i);
            filmList.add(film);
        }
        return filmList;
    }
    public int getPageByType(String type){
        int count = 0;
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "select ceiling(CONVERT(float,count(p.ID_PHIM))/24) from PHIM p where p.LOAI = '"+type+"'";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPage(){
        int count = 0;
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "select ceiling(CONVERT(float,count(p.ID_PHIM))/24) from PHIM p";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int findMaxId() {
        return filmRepository.findMaxId();
    }
    public List<Film> searchFilm(String keyword) {
        return filmRepository.searchByFilmName(keyword);

    }
    public List<Film> getMyFilm(int id) {
        return filmRepository.getMyFilm(id);
    }
    public Film findFilmMaxID(){
        return filmRepository.findFilmByID(filmRepository.findMaxId());
    }

}
