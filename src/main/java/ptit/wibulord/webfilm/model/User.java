package ptit.wibulord.webfilm.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name="NGUOIDUNG")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
