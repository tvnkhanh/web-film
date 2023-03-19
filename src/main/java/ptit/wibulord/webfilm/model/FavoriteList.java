package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity()
@Table(name="DSYEUTHICH")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DSYT")
    private int idFavoriteList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ND")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CT_YEUTHICH",
            joinColumns = {@JoinColumn(name = "ID_DSYT")},
            inverseJoinColumns = {@JoinColumn(name = "ID_PHIM")})
    private Collection<Film> films;
}
