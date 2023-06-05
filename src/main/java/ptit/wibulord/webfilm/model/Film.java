package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import ptit.wibulord.webfilm.dto.Databasehelper;
import ptit.wibulord.webfilm.service.FilmService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Entity()
@Table(name="PHIM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHIM")
    private int filmID;
    @Column(name = "TENPHIM")
    private String filmName;
    @Column(name = "ANH")
    private String imgPath;
    @Column(name = "ANH_BXH")
    private String imgTierList;
    @Column(name = "MOTA")
    private String describe;
    @Column(name = "LOAI")
    private String type;
    @Column(name = "GIA")
    private int price;
    @ManyToMany(mappedBy = "films")
    private Collection<WatchList> watchLists;
    @ManyToMany(mappedBy = "films")
    private Collection<FavoriteList> favoriteLists;

    @OneToMany(mappedBy = "film")
    private Collection<Episode> episodeList;
    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Collection<BuyFilm> buyFilm;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CT_THELOAI",
            joinColumns = {@JoinColumn(name = "ID_PHIM")},
            inverseJoinColumns = {@JoinColumn(name = "ID_TL")})
    private Collection<Category> categoryList;

    public int getFilmView(int filmID) {
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT SUM(LUOTXEM) AS LUOTXEM FROM TAP WHERE ID_PHIM = " + filmID;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getNumberOfEp(int filmID) {
        try {
            Connection con = Databasehelper.openConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT COUNT(ID_TAP) FROM TAP WHERE ID_PHIM = " + filmID;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int checkFilmInMyList(int id){
        List<Film> myFilm = new FilmService().getMyFilm(id);
        for(BuyFilm buy : buyFilm ){
            if(buy.getFilm().getFilmID() == id)return 1;

        }
        return 0;
    }
}
