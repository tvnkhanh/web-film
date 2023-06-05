package ptit.wibulord.webfilm.model;


import jakarta.persistence.*;
import lombok.*;
import ptit.wibulord.webfilm.service.FilmService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity()
@Table(name="NGUOIDUNG")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ND")
    private int idUser;
    @Column(name = "HOTEN")
    private String fullName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name="ANH")
    private String imgPath;
    @Column(name = "GIOITINH")
    private String gender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEN_TK")
    private Account account;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private WatchList watchList;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private FavoriteList favoriteList;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<DetailPurchase> detailPurchases;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<BuyFilm> buyFilm;
    public int totalPoint(){
        int total = 0;
        for(DetailPurchase detail : detailPurchases){
            total+= detail.getPoint();
        };
        for(BuyFilm buy : buyFilm){
            total-= buy.getPrice();
        };
        return total;
    }
    public User(int idUser) {
        this.idUser = idUser;
    }
    public int checkFilmInMyList(int id){
        for(BuyFilm buy : buyFilm ){
            if(buy.getFilm().getFilmID() == id)return 1;
        }
        return 0;
    }
}
