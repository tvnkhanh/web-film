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
    @ManyToMany(mappedBy = "films")
    private Collection<WatchList> watchLists;
    @ManyToMany(mappedBy = "films")
    private Collection<FavoriteList> favoriteLists;

    @OneToMany(mappedBy = "film")
    private Collection<Episode> episodeList;

    @ManyToMany(mappedBy = "filmList")
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
}
