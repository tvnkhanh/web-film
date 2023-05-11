package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "CT_THELOAI",
            joinColumns = {@JoinColumn(name = "ID_PHIM")},
            inverseJoinColumns = {@JoinColumn(name = "ID_TL")})
    private Collection<Category> categoryList;
}
